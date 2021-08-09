package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "movie_category")
public class MovieCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_category_id")
    Long movieCategoryId;

    @Column(name = "movie_category")
    String movieCategory;

}
