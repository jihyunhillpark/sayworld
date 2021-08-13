package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="room_keyword")
@Getter
@Setter
@Builder
@NoArgsConstructor //public이라서 추가
@AllArgsConstructor
public class RoomTag {

    @EmbeddedId
    private RoomTagID roomTagID;

    @MapsId("roomId") // RoomPK 의 roomId와 매핑
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @MapsId("tagId") //RoomPK의 tagId와 매핑
    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Tag tag;

}
