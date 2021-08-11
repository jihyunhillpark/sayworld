package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomService {
    Room createRoom(RoomCreatePostRequest roomCreateInfo);
    // Room updateRoom(RoomUpdateRequest roomUpdateInfo);
    Optional<Room> getRoomBySessionId(String sessionId);
    void addTags(List<String> keywords, Room room);
    void deleteRoom(Room selectedRoom);
    Optional<Room> detailRoom(String sessionId);
    List<Room> getRoomList();
    List<Room> getRoomByRoomTitle(String roomName);
    List<Room> getRoomByHostNickname(String nickname);
    Set<Room> getRoomListByKeyword(String keyword);
}
