package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = 846449565L;

    public static final QRoom room = new QRoom("room");

    public final NumberPath<Integer> bookCategoryId = createNumber("bookCategoryId", Integer.class);

    public final NumberPath<Integer> movieCategoryId = createNumber("movieCategoryId", Integer.class);

    public final NumberPath<Integer> roomHostId = createNumber("roomHostId", Integer.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final StringPath roomImg = createString("roomImg");

    public final StringPath roomInviteCode = createString("roomInviteCode");

    public final StringPath roomPassword = createString("roomPassword");

    public final StringPath roomTitle = createString("roomTitle");

    public final StringPath sessionId = createString("sessionId");

    public final SetPath<Tag, QTag> tags = this.<Tag, QTag>createSet("tags", Tag.class, QTag.class, PathInits.DIRECT2);

    public QRoom(String variable) {
        super(Room.class, forVariable(variable));
    }

    public QRoom(Path<? extends Room> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoom(PathMetadata metadata) {
        super(Room.class, metadata);
    }

}

