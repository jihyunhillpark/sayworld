package com.ssafy.api.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ssafy.db.entity.RoomTag;
import com.ssafy.db.entity.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@Builder
@NoArgsConstructor //public이라서 추가
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class RoomRes {

    @ApiModelProperty("room response roomId")
    private long roomId;

    @ApiModelProperty("room response room title")
    private String roomName;

    @ApiModelProperty("room response hostNickname")
    private String hostNickname;

    @ApiModelProperty("room response tags")
    private List<String> keywords;

    @ApiModelProperty("room response limit")
    private int limit;

    @ApiModelProperty("room response movieCategory")
    private String movieCategory;

    @ApiModelProperty("room response bookCategory")
    private String bookCategory; //fk

    @ApiModelProperty("room response room invitecode")
    private String roomInviteCode;

    @ApiModelProperty("room response room password")
    private String password;

    @ApiModelProperty("room response thumbnail img url")
    private String thumbnailUrl;

    @ApiModelProperty("room response session id")
    private String sessionId;

}
