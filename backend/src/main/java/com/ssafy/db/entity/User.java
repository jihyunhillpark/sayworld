package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    long userId;

    @Column(name = "password")
    String password;
    @Column(name = "nickname")
    String nickname;
    @Column(name = "email")
    String email;
    @Column(name = "gender")
    String gender;
    @Column(name = "age")
    String age;
    @Column(name = "default_page")
    String defaultPage;
    @Column(name = "tier_book")
    String tierBook;
    @Column(name = "tier_movie")
    String tierMovie;
    @Column(name = "profile_img")
    String profileImg;



//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    String password;
}
