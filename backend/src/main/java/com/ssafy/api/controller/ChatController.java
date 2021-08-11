package com.ssafy.api.controller;


import com.ssafy.api.response.ChatMessage;
import com.ssafy.api.service.UserServiceImpl;
import com.ssafy.db.entity.ChatFriendMsg;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    @Autowired
    UserRepository userRepository;

    private final SimpMessagingTemplate template; // 특정 Broker로 메세지 전달

    // Client가 Send할 수 있는 경로
    // stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    // "/publish/chat/enter"
    @MessageMapping("/chat/enter")
    public void enter(ChatMessage message){
        User user = userRepository.findByUserId(message.getUserId()).get();
        message.setChatMessage(user.getNickname() + "님이 채팅방에 입장했습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }
}
