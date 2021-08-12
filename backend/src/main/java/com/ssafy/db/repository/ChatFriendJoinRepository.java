package com.ssafy.db.repository;

import com.ssafy.db.entity.ChatFriendJoin;
import com.ssafy.db.entity.ChatFriendRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatFriendJoinRepository extends JpaRepository<ChatFriendJoin, Long> {
    Optional<ChatFriendJoin> findByUser_UserId(Long userId);
    Optional<ChatFriendRoom> findByChatFriendRoom_ChatFriendRoomId(Long chatFriendRoomId);
}
