package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat_friend_room")
@DynamicInsert
@NoArgsConstructor
public class ChatFriendRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_friend_room_id")
    Long chatFriendRoomId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_friend_room_id")
    List<ChatFriendJoin> chatFriendJoins = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_friend_room_id")
    List<ChatFriendMsg> chatFriendMsgs = new ArrayList<>();

}
