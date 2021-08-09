package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@NoArgsConstructor
@ToString
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
    @Column(name = "blog_id")
    Long blogId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;

    @Column(name = "blog_title")
    String blogTitle;
    @Column(name = "blog_content")
    String blogContent;
    @Column(name = "blog_created_at")
    Date blogCreatedAt;
    @Column(name = "blog_lock")
    int blogLock;
    @Column(name = "blog_category")
    int blogCategory;


}
