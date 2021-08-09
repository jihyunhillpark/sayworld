package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMovieCategory is a Querydsl query type for MovieCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMovieCategory extends EntityPathBase<MovieCategory> {

    private static final long serialVersionUID = 185851852L;

    public static final QMovieCategory movieCategory1 = new QMovieCategory("movieCategory1");

    public final StringPath movieCategory = createString("movieCategory");

    public final NumberPath<Long> movieCategoryId = createNumber("movieCategoryId", Long.class);

    public QMovieCategory(String variable) {
        super(MovieCategory.class, forVariable(variable));
    }

    public QMovieCategory(Path<? extends MovieCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovieCategory(PathMetadata metadata) {
        super(MovieCategory.class, metadata);
    }

}

