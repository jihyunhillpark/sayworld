package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "blog_id")
    Long blogId;

    @Column(name = "blog_title")
    String blogTitle;

}
