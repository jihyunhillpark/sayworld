package com.ssafy.db.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@DynamicInsert
@NoArgsConstructor
@Table(name = "culture_power")
public class Culture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "culture_power_id")
    Long culturePowerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)// 양방향 관계에서 한쪽은 읽기만 할수있게 설정.
    User user;

    @Column(name = "culture_category")
    Long cultureCategory;

    @Column(name = "culture_title")
    String cultureTitle;
}
