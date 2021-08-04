package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomService {
    //List<Room> selectRooms();
    Room createRoom(RoomCreatePostRequest roomCreateInfo, String sessionId);
    // Room updateRoom(RoomUpdateRequest roomUpdateInfo);
    Optional<Room> getRoomByRoomTitle(String roomName);
    Optional<Room> getRoomBySessionId(String sessionId);
    List<Tag> addTags(List<String> keywords, Long roomId);
    void deleteRoom(Room selectedRoom);
    Optional<Room> detailRoom(String sessionId);
}
