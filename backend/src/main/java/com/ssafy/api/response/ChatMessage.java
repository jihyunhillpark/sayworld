package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@ApiModel("Chat Message")
public class ChatMessage {
    @ApiModelProperty("chat message Id")
    Long chatMessageId;
    @ApiModelProperty("chat Room Id")
    Long chatRoomId;
    @ApiModelProperty("chat writer Id")
    Long userId;
    @ApiModelProperty("chat message text")
    String chatMessage;
    @ApiModelProperty("chat message date")
    Date chatCreatedAt;
    @ApiModelProperty("chat message file url")
    String chatImg;
}
