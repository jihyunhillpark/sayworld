package com.ssafy.api.controller;

import com.ssafy.db.entity.ChatFriendRoom;
import com.ssafy.db.repository.ChatFriendJoinRepository;
import com.ssafy.db.repository.ChatFriendRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Log4j2
public class ChatRoomController {
    @Autowired
    ChatFriendJoinRepository chatFriendJoinRepository;
    @Autowired
    ChatFriendRoomRepository chatFriendRoomRepository;

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public ModelAndView rooms(){
        log.info("#All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");
        mv.addObject("list", chatFriendRoomRepository.findAll());
        return mv;
    }

    @PostMapping("/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        log.info("# Create Chat Room , name: " + name);
        ChatFriendRoom newRoom = new ChatFriendRoom();
        rttr.addFlashAttribute("roomName", newRoom);

        return "redirect:/chat/rooms";
    }

    @GetMapping("/room")
    public void getRoom(Long roomId, Model model){
        log.info("#Create Chat Room roomId: " + roomId);
        model.addAttribute("room",chatFriendRoomRepository.findByChatFriendRoomId(roomId));
    }
}
