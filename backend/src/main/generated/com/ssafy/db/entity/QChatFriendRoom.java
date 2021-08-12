package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatFriendRoom is a Querydsl query type for ChatFriendRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatFriendRoom extends EntityPathBase<ChatFriendRoom> {

    private static final long serialVersionUID = -1681530285L;

    public static final QChatFriendRoom chatFriendRoom = new QChatFriendRoom("chatFriendRoom");

    public final ListPath<ChatFriendJoin, QChatFriendJoin> chatFriendJoins = this.<ChatFriendJoin, QChatFriendJoin>createList("chatFriendJoins", ChatFriendJoin.class, QChatFriendJoin.class, PathInits.DIRECT2);

    public final ListPath<ChatFriendMsg, QChatFriendMsg> chatFriendMsgs = this.<ChatFriendMsg, QChatFriendMsg>createList("chatFriendMsgs", ChatFriendMsg.class, QChatFriendMsg.class, PathInits.DIRECT2);

    public final NumberPath<Long> chatFriendRoomId = createNumber("chatFriendRoomId", Long.class);

    public QChatFriendRoom(String variable) {
        super(ChatFriendRoom.class, forVariable(variable));
    }

    public QChatFriendRoom(Path<? extends ChatFriendRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatFriendRoom(PathMetadata metadata) {
        super(ChatFriendRoom.class, metadata);
    }

}

