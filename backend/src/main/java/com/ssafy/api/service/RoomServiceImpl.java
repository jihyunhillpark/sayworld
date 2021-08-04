package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.repository.RoomRepository;
import com.ssafy.db.repository.TagRepository;
import io.openvidu.java.client.Session;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public Room createRoom(RoomCreatePostRequest roomCreateInfo, String sessionId) {
        Optional<Room> existingRoom = roomRepository.findByRoomTitle(roomCreateInfo.getRoomName());
        if(existingRoom.isPresent()) {
            return null;
        }
        Room room = Room.builder().roomTitle(roomCreateInfo.getRoomName())
                .roomHostId(roomCreateInfo.getHostId())
                .roomInviteCode("request_invite_url")
                .movieCategoryId(roomCreateInfo.getMovieCategory())
                .bookCategoryId(roomCreateInfo.getBookCategory())
                .roomPassword(roomCreateInfo.getPassword())
                .roomImg("request_thumbnail_img")
                .sessionId(sessionId).build();
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomByRoomTitle(String roomName) {
        return roomRepository.findByRoomTitle(roomName);
    }

    @Override
    public Optional<Room> getRoomBySessionId(String sessionId) {
        return roomRepository.findBySessionId(sessionId);
    }

    @Override
    public List<Tag> addTags(List<String> keywords, Long roomId) {
        List<Tag> mappedTags = new ArrayList<>();
        for(String keyword: keywords){
            if(tagRepository.findByTagName(keyword).isPresent()) continue;
            Tag tag =  tagRepository.save(Tag.builder().tagName(keyword).build()); //새로운거 추가.
            mappedTags.add(tag);
        }
        return mappedTags;
    }

    @Override
    public void deleteRoom(Room selectedRoom) {
        roomRepository.delete(selectedRoom);
    }

    @Override
    public Optional<Room> detailRoom(String sessionId) {
        return roomRepository.findBySessionId(sessionId);
    }
}
