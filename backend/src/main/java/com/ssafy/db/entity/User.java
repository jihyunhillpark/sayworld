package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
//@DynamicUpdate // update 할때 변경된 필드만 적용. 없으면 변경하지 않은 값들은 전부 null로 들어감
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
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
    int age;
    @Column(name = "default_page")
    String defaultPage;
    @Column(name = "tier_book")
    int tierBook;
    @Column(name = "tier_movie")
    int tierMovie;
    @Column(name = "profile_img")
    String profileImg;



//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    String password;
}
