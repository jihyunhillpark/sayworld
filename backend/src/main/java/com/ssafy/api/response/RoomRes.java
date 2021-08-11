package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본방(room) 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("RoomRes")
public class RoomRes {
    @ApiModelProperty(name = "화상 채팅방 이름")
    String
}
