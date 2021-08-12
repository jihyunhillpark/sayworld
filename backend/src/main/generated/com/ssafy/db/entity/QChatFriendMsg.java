package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatFriendMsg is a Querydsl query type for ChatFriendMsg
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatFriendMsg extends EntityPathBase<ChatFriendMsg> {

    private static final long serialVersionUID = 2023962377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatFriendMsg chatFriendMsg1 = new QChatFriendMsg("chatFriendMsg1");

    public final DateTimePath<java.util.Date> chatFriendCreatedAt = createDateTime("chatFriendCreatedAt", java.util.Date.class);

    public final StringPath chatFriendImg = createString("chatFriendImg");

    public final StringPath chatFriendMsg = createString("chatFriendMsg");

    public final NumberPath<Long> chatFriendMsgId = createNumber("chatFriendMsgId", Long.class);

    public final QChatFriendRoom room;

    public final QUser user;

    public QChatFriendMsg(String variable) {
        this(ChatFriendMsg.class, forVariable(variable), INITS);
    }

    public QChatFriendMsg(Path<? extends ChatFriendMsg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatFriendMsg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatFriendMsg(PathMetadata metadata, PathInits inits) {
        this(ChatFriendMsg.class, metadata, inits);
    }

    public QChatFriendMsg(Class<? extends ChatFriendMsg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QChatFriendRoom(forProperty("room")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

