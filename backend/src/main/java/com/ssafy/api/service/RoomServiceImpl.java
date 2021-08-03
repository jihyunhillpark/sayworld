package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.db.entity.Room;
import com.ssafy.db.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room createRoom(RoomCreatePostRequest roomCreateInfo, String openviduToken) {
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
                .openviduToken(openviduToken).build();
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomByRoomTitle(String roomTitle) {
        return roomRepository.findByRoomTitle(roomTitle);
    }

    @Override
    public void deleteRoom(String roomTitle) {

    }
}
