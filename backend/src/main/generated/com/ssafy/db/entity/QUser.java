package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 846542477L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final SetPath<User, QUser> blacks = this.<User, QUser>createSet("blacks", User.class, QUser.class, PathInits.DIRECT2);

    public final ListPath<Blog, QBlog> blogs = this.<Blog, QBlog>createList("blogs", Blog.class, QBlog.class, PathInits.DIRECT2);

    public final ListPath<Culture, QCulture> cultures = this.<Culture, QCulture>createList("cultures", Culture.class, QCulture.class, PathInits.DIRECT2);

    public final StringPath defaultPage = createString("defaultPage");

    public final StringPath email = createString("email");

    public final SetPath<User, QUser> friends = this.<User, QUser>createSet("friends", User.class, QUser.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profileImg = createString("profileImg");

    public final NumberPath<Integer> tierBook = createNumber("tierBook", Integer.class);

    public final NumberPath<Integer> tierMovie = createNumber("tierMovie", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

