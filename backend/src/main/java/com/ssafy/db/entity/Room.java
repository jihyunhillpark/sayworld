package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

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

    @Column(name="session_id", nullable = false)
    private String sessionId;

    @ManyToMany
    @JoinTable(name = "room_keyword",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name="keyword_id"))
    final private Set<Tag> tags = new HashSet<Tag>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getRooms().add(this);
    }

    public void removeTag(Tag tag){
        tags.remove(tag);
        tag.getRooms().remove(this);
    }

}
