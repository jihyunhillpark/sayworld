package com.ssafy.api.service;

import com.ssafy.api.request.RoomCreatePostRequest;
import com.ssafy.api.response.RoomRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
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

    @Autowired
    RoomTagRepository roomTagRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    MovieCategoryRepository movieCategoryRepository;

    @Override
    public List<RoomRes> getRoomList() {
        List<Room> rooms = roomRepository.findAll();
        return makeRoomResponseList(rooms);
    }

    @Override
    public Room createRoom(RoomCreatePostRequest roomCreateInfo) {
        Optional<Room> existingRoom = roomRepository.findByRoomTitle(roomCreateInfo.getRoomName());
        if(existingRoom.isPresent()) {
            return null;
        }
        Long bookCategoryId = ( roomCreateInfo.getBookCategoryId() == null)? 0L: roomCreateInfo.getBookCategoryId();
        Long movieCategoryid = ( roomCreateInfo.getMovieCategoryId() == null)? 0L: roomCreateInfo.getMovieCategoryId();
        Room room = Room.builder().roomTitle(roomCreateInfo.getRoomName())
                .hostId(roomCreateInfo.getHostId())
                .roomInviteCode("request_invite_url")
                .movieCategoryId(movieCategoryid)
                .bookCategoryId(bookCategoryId)
                .roomPassword(roomCreateInfo.getPassword())
                .roomImg(roomCreateInfo.getThumbnailUrl())
                .sessionId(roomCreateInfo.getSessionId() + roomCreateInfo.getRoomName()).build();
        addTags(roomCreateInfo.getKeywords(), room);  //키워드가 있다면 db에 추가

//        Optional<User> user = userRepository.findByUserId(roomCreateInfo.getHostId());
//        if(user.isPresent()) { //hostId로 유저를 찾아서 관계 테이블들에 쏙쏙
//            User insertUser = user.get();
//            room.setUser(insertUser);
//        }
        return room;
    }
    @Override
    public void addTags(List<String> keywords, Room room) {
        Optional<Tag> wrappedTag = null;
        Tag tag;

        for(String keyword: keywords){
            System.out.println("KEYWORD ==== " + keyword);
            wrappedTag = tagRepository.findByTagName(keyword);
            if(wrappedTag.isPresent()) {
                tag = wrappedTag.get();
            }
            else{
                tag = Tag.builder().tagName(keyword).build();
                tagRepository.save(tag); //기존에 없던 태그라면 태그 테이블에 추가.
            }
            RoomTagID roomTagID = new RoomTagID(room.getRoomId(),tag.getTagId());
            RoomTag roomTag = new RoomTag(roomTagID,room,tag);
            roomRepository.save(room);
            roomTagRepository.save(roomTag);
        }
    }

    @Override
    public void deleteRoom(Room selectedRoom) {
        roomRepository.delete(selectedRoom);
    }

    @Override
    public Optional<Room> getRoomByRoomId(Long roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @Override
    public RoomRes detailRoom(Long roomId) {
        RoomRes roomRes = null;
        Optional<Room> roomOpt = roomRepository.findByRoomId(roomId);
        if(roomOpt.isPresent()){
            Room room = roomOpt.get();
            Optional<User> userOpt = userRepository.findByUserId(room.getHostId());
            if(userOpt.isPresent()){
                roomRes = makeRoomResponse(room,userOpt.get());
            }
        }
        return roomRes;
    }

    @Override //TO-DO : 유저부분 one-to-many 설정해줘야함? ㅇㅇ - DONE
    public List<RoomRes> getRoomListByHostNickname(@Param("nickname") String nickname) {
        List<Room> rooms = null;
        List<RoomRes> roomResList = new ArrayList<>();
        Optional<User> user = userRepository.findByNickname(nickname);

        if(user.isPresent()){
            User searchedUser = user.get();
            rooms = roomRepository.findByHostId(searchedUser.getUserId());
            for(Room room : rooms){
                RoomRes roomRes = makeRoomResponse(room,searchedUser);
                roomResList.add(roomRes);
            }
        }
        return roomResList;
    }

    @Override
    public List<RoomRes> getRoomListByKeyword(String keyword) {
        //해당 키워드로 검색
        Optional<Tag> find = tagRepository.findByTagName(keyword);
        List<Room> rooms = new ArrayList<>();
        if(find.isPresent()){
            List<RoomTag> roomTags = roomTagRepository.findRoomTagsByRoomTagIDTagId(find.get().getTagId());
            for(RoomTag roomTag : roomTags){
                // System.out.println("ROOM == " + roomTag.getRoom().getRoomTitle());
                rooms.add(roomTag.getRoom());
            }
        }
        return makeRoomResponseList(rooms);
    }
    @Override
    public List<RoomRes> getRoomListByRoomTitle(String roomName) {
        List<Room> rooms = roomRepository.findByRoomTitleContains(roomName);
        return makeRoomResponseList(rooms);
    }

//    @Override
//    public Optional<Room> getRoomBySessionId(String sessionId) {
//        return roomRepository.findBySessionId(sessionId);
//    }

    @Override
    public List<RoomRes> getRoomListByMovieId(Long movieId) {
        List<Room> rooms = roomRepository.findByMovieCategoryId(movieId);
        return makeRoomResponseList(rooms);
    }

    @Override
    public List<RoomRes> getRoomListByBookId(Long bookId) {
        List<Room> rooms = roomRepository.findByBookCategoryId(bookId);
        return makeRoomResponseList(rooms);
    }

    private List<RoomRes> makeRoomResponseList(List<Room> rooms){
        List<RoomRes> roomResList = new ArrayList<>();
        for(Room room : rooms) {
            Optional<User> userOpt = userRepository.findByUserId(room.getHostId());
            if (userOpt.isPresent()) {
                roomResList.add(makeRoomResponse(room, userOpt.get()));
            }
        }
        return roomResList;
    }
    private RoomRes makeRoomResponse(Room room, User user) {
        Optional<BookCategory> bookCategory = bookCategoryRepository.findById(room.getBookCategoryId());
        Optional<MovieCategory> movieCategory = movieCategoryRepository.findById(room.getMovieCategoryId());
        List<String> keywords = new ArrayList<>();

        RoomRes roomRes = RoomRes.builder()
                .roomId(room.getRoomId())
                .roomName(room.getRoomTitle())
                .hostNickname(user.getNickname())
                .limit(5)
                .password(room.getRoomPassword())
                .thumbnailUrl(room.getRoomImg())
                .sessionId(room.getSessionId()).build();

        Iterator<RoomTag> roomTags =  room.getRoomTags().iterator();
        while(roomTags.hasNext())
            keywords.add(roomTags.next().getTag().getTagName());
        roomRes.setKeywords(keywords);

        if(bookCategory.isPresent()) roomRes.setBookCategory(bookCategory.get().getBookCategory());
        if(movieCategory.isPresent()) roomRes.setMovieCategory(movieCategory.get().getMovieCategory());

        return roomRes;
    }
}
