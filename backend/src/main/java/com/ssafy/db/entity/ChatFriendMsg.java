package com.ssafy.db.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "chat_friend_message")
@DynamicInsert
@NoArgsConstructor
public class ChatFriendMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_friend_message_id")
    Long chatFriendMsgId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_friend_room_id", insertable = false, updatable = false)
    ChatFriendRoom room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;

    @Column(name = "chat_friend_message")
    String chatFriendMsg;
    @Column(name = "chat_friend_created_at")
    Date chatFriendCreatedAt;
    @Column(name = "chat_friend_img")
    String chatFriendImg;


}
