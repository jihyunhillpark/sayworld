package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name="keyword")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="keyword_id")
    private int tagId;


    @Column(nullable = false, name="keyword_name")
    private String tagName;

    /* 추천알고리즘에서 사용될 것 같아 넣었지만, 현재로서는 keyword에서 room을 탐색할 필요가 없어서 안 넣어도 된다. */
    @ManyToMany(mappedBy = "tags")
    final private Set<Room> rooms = new HashSet<>();
}
