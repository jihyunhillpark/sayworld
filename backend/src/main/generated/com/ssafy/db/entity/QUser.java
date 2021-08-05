package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 846542477L;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

<<<<<<< HEAD
    public final StringPath department = createString("department");
=======
    public final SetPath<User, QUser> blacks = this.<User, QUser>createSet("blacks", User.class, QUser.class, PathInits.DIRECT2);

    public final ListPath<Culture, QCulture> cultures = this.<Culture, QCulture>createList("cultures", Culture.class, QCulture.class, PathInits.DIRECT2);
>>>>>>> 11aec8f9994454f92f9e5575ec41ab6d05f56107

    //inherited
    public final NumberPath<Long> id = _super.id;

<<<<<<< HEAD
    public final StringPath name = createString("name");
=======
    public final StringPath email = createString("email");

    public final SetPath<User, QUser> friends = this.<User, QUser>createSet("friends", User.class, QUser.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    public final StringPath nickname = createString("nickname");
>>>>>>> 11aec8f9994454f92f9e5575ec41ab6d05f56107

    public final StringPath password = createString("password");

    public final StringPath position = createString("position");

    public final StringPath userId = createString("userId");

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

