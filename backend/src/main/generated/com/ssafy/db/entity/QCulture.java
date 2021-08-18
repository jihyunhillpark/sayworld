package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCulture is a Querydsl query type for Culture
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCulture extends EntityPathBase<Culture> {

    private static final long serialVersionUID = 567671724L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCulture culture = new QCulture("culture");

    public final NumberPath<Long> cultureCategory = createNumber("cultureCategory", Long.class);

    public final NumberPath<Long> culturePowerId = createNumber("culturePowerId", Long.class);

    public final StringPath cultureTitle = createString("cultureTitle");

    public final QUser user;

    public QCulture(String variable) {
        this(Culture.class, forVariable(variable), INITS);
    }

    public QCulture(Path<? extends Culture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCulture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCulture(PathMetadata metadata, PathInits inits) {
        this(Culture.class, metadata, inits);
    }

    public QCulture(Class<? extends Culture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

