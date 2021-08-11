package com.ssafy.db.repository;

import com.ssafy.db.entity.ChatFriendRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatFriendRoomRepository extends JpaRepository<ChatFriendRoom, Long> {
    Optional<ChatFriendRoom> findByChatFriendRoomId(Long chatFriendRoomId);
    //List<ChatFriendRoom> findAll();
}
