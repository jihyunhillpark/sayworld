package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatFriendJoin is a Querydsl query type for ChatFriendJoin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatFriendJoin extends EntityPathBase<ChatFriendJoin> {

    private static final long serialVersionUID = -1681768798L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatFriendJoin chatFriendJoin = new QChatFriendJoin("chatFriendJoin");

    public final NumberPath<Long> chatFriendJoinId = createNumber("chatFriendJoinId", Long.class);

    public final QChatFriendRoom room;

    public final QUser user;

    public QChatFriendJoin(String variable) {
        this(ChatFriendJoin.class, forVariable(variable), INITS);
    }

    public QChatFriendJoin(Path<? extends ChatFriendJoin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatFriendJoin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatFriendJoin(PathMetadata metadata, PathInits inits) {
        this(ChatFriendJoin.class, metadata, inits);
    }

    public QChatFriendJoin(Class<? extends ChatFriendJoin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QChatFriendRoom(forProperty("room")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

