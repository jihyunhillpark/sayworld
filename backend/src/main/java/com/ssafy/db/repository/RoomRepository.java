package com.ssafy.db.repository;

import com.ssafy.db.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Room 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
*/
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomTitle(String roomTitle);
    Optional<Room> findBySessionId(String SessionId);
    List<Room> findByRoomTitleContains(String roomTitle);
    @Query("SELECT DISTINCT r FROM Room r JOIN FETCH r.tags tag WHERE tag.tagName = :tagName")
    List<Room> findByKeyword(@Param("tagName") String keyword);
}
