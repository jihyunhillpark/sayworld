package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@Entity
@Table(name="keyword")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="keyword_id")
    private Long tagId;


    @Column(nullable = false, name="keyword_name")
    private String tagName;

    /* 추천알고리즘에서 사용될 것 같아 넣었지만, 현재로서는 keyword에서 room을 탐색할 필요가 없어서 안 넣어도 된다. */
//    @OneToMany(mappedBy = "tag")
//    final private Set<RoomTag> roomTags = new HashSet<>();
}
