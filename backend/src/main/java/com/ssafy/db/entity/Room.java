package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor //public이라서 추가
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id",nullable = false, updatable = false) //openvidu token 사용해도 될둣..?
    private long roomId;

    //시간 안해도 되나!?

    @Column(name="room_host_id", nullable = false)
    private int roomHostId; //fk

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

    @Column(name="openvidu_token", nullable = false)
    private String openviduToken;

    @ManyToMany
    @JoinTable(name = "room_keword",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name="keyword_id"))
    final private List<Tag> tags = new ArrayList<Tag>();

}
