package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookCategory is a Querydsl query type for BookCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookCategory extends EntityPathBase<BookCategory> {

    private static final long serialVersionUID = -1532310583L;

    public static final QBookCategory bookCategory1 = new QBookCategory("bookCategory1");

    public final StringPath bookCategory = createString("bookCategory");

    public final NumberPath<Long> bookCategoryId = createNumber("bookCategoryId", Long.class);

    public QBookCategory(String variable) {
        super(BookCategory.class, forVariable(variable));
    }

    public QBookCategory(Path<? extends BookCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookCategory(PathMetadata metadata) {
        super(BookCategory.class, metadata);
    }

}

