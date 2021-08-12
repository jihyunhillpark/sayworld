package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@ToString
@NoArgsConstructor
//@DynamicUpdate // update 할때 변경된 필드만 적용. 없으면 변경하지 않은 값들은 전부 null로 들어감
@DynamicInsert
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
    @Column(name = "user_id")
    Long userId;

    @Column(name = "password")
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
    String defaultPage;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<ChatFriendJoin> chatFriendJoins = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<ChatFriendMsg> chatFriendMsgs = new ArrayList<>();

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

//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    String password;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//    private Set<Room> rooms = new HashSet<>();
//
//    public void addRoom(Room room){
//        rooms.add(room);
//        room.setUser(this);
//    }
}
