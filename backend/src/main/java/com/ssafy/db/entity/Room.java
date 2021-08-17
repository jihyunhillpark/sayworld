package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    //시간 안해도 되나!?

    @Column(name="room_title")
    private String roomTitle;

    @Column(name="room_host_id")
    private long hostId;
//    @ManyToOne
//    @JoinColumn(name="room_host_id")
//    private User user;

    @Column(name="room_invite_code")
    private String roomInviteCode;

    @Column(name="movie_category_id")
    @ColumnDefault("0")
    private Long movieCategoryId; //fk

    @Column(name="book_category_id")
    @ColumnDefault("0")
    private Long bookCategoryId; //fk

    @Column(name="room_password")
    private String roomPassword;

    @Column(name="room_img")
    private String roomImg;

    @Column(name="session_id", nullable = false)
    private String sessionId;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
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
//    public void setUser(User user) {
//        this.user = user;
//    }
}
