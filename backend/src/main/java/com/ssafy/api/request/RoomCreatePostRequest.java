package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Room 생성 API ([Post] /rooms/)
 * */
@Getter
@Setter
@ApiModel("RoomCreatePostRequest")
public class RoomCreatePostRequest {
    @ApiModelProperty(name="방 제목", example="해리포터 모여라~")
    String roomName;
    @ApiModelProperty(name="호스트 ID", example="ssafy_web")
    String hostUser;
}
