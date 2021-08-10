package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomTag is a Querydsl query type for RoomTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoomTag extends EntityPathBase<RoomTag> {

    private static final long serialVersionUID = 826079933L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomTag roomTag = new QRoomTag("roomTag");

    public final QRoom room;

    public final QRoomTagID roomTagID;

    public final QTag tag;

    public QRoomTag(String variable) {
        this(RoomTag.class, forVariable(variable), INITS);
    }

    public QRoomTag(Path<? extends RoomTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomTag(PathMetadata metadata, PathInits inits) {
        this(RoomTag.class, metadata, inits);
    }

    public QRoomTag(Class<? extends RoomTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room")) : null;
        this.roomTagID = inits.isInitialized("roomTagID") ? new QRoomTagID(forProperty("roomTagID")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag")) : null;
    }

}

