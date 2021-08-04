package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@DynamicInsert
@Table(name = "culture_power")
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "culture_power_id")
    Long culturePowerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)// 양방향 관계에서 한쪽은 읽기만 할수있게 설정.
    private User user;

    @Column(name = "culture_category")
    char cultureCategory;

    @Column(name = "culture_title")
    String cultureTitle;
}
