package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

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
    String defaultPage;
    @Column(name = "tier_book")
    int tierBook;
    @Column(name = "tier_movie")
    int tierMovie;
    @Column(name = "profile_img")
    String profileImg;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" )
    List<Culture> cultures = new ArrayList();

    @ManyToMany
    @JoinTable(name = "friend_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "black_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "block_user"))
    private Set<User> blacks = new HashSet<>();




//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    String password;
}
