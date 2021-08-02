package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomDto {
    int host_id; //fk
    String room_title;
    String room_invite_code;
    int movie_category_id; //fk
    int book_category_id; //fk
    boolean room_lock;
    String room_img;

}
