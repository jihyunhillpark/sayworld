package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
>>>>>>> develop

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class User extends BaseEntity{
    String position;
    String department;
    String name;
    String userId;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @Column(name = "nickname")
    String nickname;
    @Column(name = "email")
    String email;
    @Column(name = "gender")
    String gender;
    @Column(name = "age")
    int age;
    @Column(name = "default_page")
    int defaultPage;
    @Column(name = "tier_book")
    int tierBook;
    @Column(name = "tier_movie")
    int tierMovie;
    @Column(name = "profile_img")
    String profileImg;

    // 문화력
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    List<Culture> cultures = new ArrayList();

    // 블로그
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Blog> blogs = new ArrayList();

    // 화상방 외부 채팅
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    List<ChatFriendJoin> chatFriendJoins = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    List<ChatFriendMsg> chatFriendMsgs = new ArrayList<>();

    // 친구목록
    @ManyToMany
    @JoinTable(name = "friend_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends = new HashSet<>();
    // 차단목록
    @ManyToMany
    @JoinTable(name = "black_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "block_user"))
    private Set<User> blacks = new HashSet<>();

    // 사용자 관심사(책)
    @ManyToMany
    @JoinTable(name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_category_id"))
    private Set<BookCategory> bookCategories = new HashSet<>();
    // 사용자 관심사(영화)
    @ManyToMany
    @JoinTable(name = "user_movie",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_category_id"))
    private Set<MovieCategory> movieCategories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_host_id" )
    private List<Room> rooms = new ArrayList<>();
//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    String password;
}
