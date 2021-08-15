package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.api.response.RoomRes;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.Tag;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomService {
    Room createRoom(RoomCreatePostRequest roomCreateInfo);
    // Room updateRoom(RoomUpdateRequest roomUpdateInfo);
    void addTags(List<String> keywords, Room room);
    void deleteRoom(Room selectedRoom);
    Optional<Room> getRoomByRoomId(Long roomId);
    RoomRes detailRoom(Long roomId);
    List<RoomRes> getRoomList(Long page);
    List<RoomRes> getRoomListByRoomTitle(String roomName);
    List<RoomRes> getRoomListByHostNickname(String nickname);
    List<RoomRes> getRoomListByKeyword(String keyword);
    List<RoomRes> getRoomListByMovieId(Long movieId);
    List<RoomRes> getRoomListByBookId(Long bookId);
}
