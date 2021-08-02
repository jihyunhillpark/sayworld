package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Room 생성 API ([Post] /rooms/)
 * */
@Getter
@Setter
public class RoomCreatePostRequest {
    String roomName;
    String hostUser;
}
