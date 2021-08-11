package com.ssafy.db.repository;

import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.RoomTag;
import com.ssafy.db.entity.RoomTagID;
import com.ssafy.db.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomTagRepository extends JpaRepository<RoomTag, RoomTagID> {
    List<RoomTag> findRoomTagsByRoomTagIDRoomId(long roomId);
    List<RoomTag> findRoomTagsByRoomTagIDTagId(long tagId);
}