package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.db.entity.Room;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.repository.RoomRepository;
import com.ssafy.db.repository.TagRepository;
import io.openvidu.java.client.Session;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    public List<Room> getRoomList() {
        return roomRepository.findAll();
    }

    @Override
    public Room createRoom(RoomCreatePostRequest roomCreateInfo) {
        Optional<Room> existingRoom = roomRepository.findByRoomTitle(roomCreateInfo.getRoomName());
        if(existingRoom.isPresent()) {
            return null;
        }
        Room room = Room.builder().roomTitle(roomCreateInfo.getRoomName())
                .roomHostId(roomCreateInfo.getHostId())
                .roomInviteCode("request_invite_url")
                .movieCategoryId(roomCreateInfo.getMovieCategoryId())
                .bookCategoryId(roomCreateInfo.getBookCategoryId())
                .roomPassword(roomCreateInfo.getPassword())
                .roomImg(roomCreateInfo.getThumbnailUrl())
                .sessionId(roomCreateInfo.getSessionId() + roomCreateInfo.getRoomName()).build();
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRoomByRoomTitle(String roomName) {
        return roomRepository.findByRoomTitleContains(roomName);
    }

    @Override
    public Optional<Room> getRoomBySessionId(String sessionId) {
        return roomRepository.findBySessionId(sessionId);
    }

    @Override
    public void addTags(List<String> keywords, Room room) {
        List<Tag> mappedTags = new ArrayList<>();
        Optional<Tag> wrappedTag;
        Tag tag;

        for(String keyword: keywords){
            wrappedTag = tagRepository.findByTagName(keyword);
            if(wrappedTag.isPresent()) {
                tag = wrappedTag.get();
                // System.out.println("tag = " + tag.getTagName() + ", id = " + tag.getTagId());
            }
            else{
                tag = Tag.builder().tagName(keyword).build();
                // System.out.println("tag = " + tag.getTagName() + ", id = " + tag.getTagId());
                tagRepository.save(tag); //기존에 없던 태그라면 태그 테이블에 추가.
            }
            RoomTagID roomTagID = new RoomTagID(room.getRoomId(),tag.getTagId());
            RoomTag roomTag = new RoomTag(roomTagID,room,tag);
            
            roomRepository.save(room);
            roomTagRepository.save(roomTag);
        }

        room.getTags().addAll(mappedTags); //매핑 테이블에 다 저장하기
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Room selectedRoom) {
        roomRepository.delete(selectedRoom);
    }

    @Override
    public Optional<Room> detailRoom(String sessionId) {
        return roomRepository.findBySessionId(sessionId);
    }

    @Override //TO-DO : 유저부분 one-to-many 설정해줘야함.
    // @Query("SELECT DISTINCT r FROM Room r JOIN FETCH r.roomHostId u WHERE u.nickname = :nickname")
    public List<Room> getRoomByHostNickname(@Param("nickname") String nickname) {
        return null;
    }

    @Override
    public List<Room> getRoomListByKeyword(String keyword) {
        return roomRepository.findByKeyword(keyword);
    }
}
