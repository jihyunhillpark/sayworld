package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.db.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    //List<Room> selectRooms();
    Room createRoom(RoomCreatePostRequest roomCreateInfo, String openviduToken);
    // Room updateRoom(RoomUpdateRequest roomUpdateInfo);
    Optional<Room> getRoomByRoomTitle(String roomTitle);
    void deleteRoom(String roomTitle);
}
