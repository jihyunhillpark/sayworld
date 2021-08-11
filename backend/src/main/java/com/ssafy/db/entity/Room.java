package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor //public이라서 추가
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id",nullable = false, updatable = false) //openvidu token 사용해도 될둣..?
    private long roomId;

    //반지의 제왕 - 판타지, 골룸골룸, 반지챡
    //해리포터 - 판타지, 흑흑, 음음

    //시간 안해도 되나!?

    @Column(name="room_host_id", nullable = false)
    private Long roomHostId; //fk

    @Column(name="room_title")
    private String roomTitle;

    @Column(name="room_invite_code")
    private String roomInviteCode;

    @Column(name="movie_category_id", nullable = false)
    private int movieCategoryId; //fk

    @Column(name="book_category_id", nullable = false)
    private int bookCategoryId; //fk

    @Column(name="room_password")
    private String roomPassword;

    @Column(name="room_img")
    private String roomImg;

    @Column(name="session_id", nullable = false)
    private String sessionId;

    @OneToMany(mappedBy = "room")
    private Set<RoomTag> roomTags;

//    public void addTag(Tag tag) {
//        tags.add(tag);
//        tag.getRooms().add(this);
//    }
//
//    public void removeTag(Tag tag){
//        tags.remove(tag);
//        tag.getRooms().remove(this);
//    }

}
