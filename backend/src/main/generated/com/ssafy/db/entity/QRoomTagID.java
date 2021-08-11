package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoomTagID is a Querydsl query type for RoomTagID
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QRoomTagID extends BeanPath<RoomTagID> {

    private static final long serialVersionUID = -706131816L;

    public static final QRoomTagID roomTagID = new QRoomTagID("roomTagID");

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public QRoomTagID(String variable) {
        super(RoomTagID.class, forVariable(variable));
    }

    public QRoomTagID(Path<? extends RoomTagID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoomTagID(PathMetadata metadata) {
        super(RoomTagID.class, metadata);
    }

}

