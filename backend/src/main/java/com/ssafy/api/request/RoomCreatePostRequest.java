package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Room 생성 API ([Post] /rooms/)
 * */
@Getter
@Setter
@ApiModel("RoomCreatePostRequest")
public class RoomCreatePostRequest {
    @ApiModelProperty(name="방 제목", example="해리포터 모여라~")
    String roomName;

    @ApiModelProperty(name="호스트 ID", example="1")
    int hostId;

    @ApiModelProperty(name="키워드 리스트", example="['판타지', '불의잔', '후회안함']")
    List<String> keywords;

    @ApiModelProperty(name="인원 수", example="4")
    int limit;

    @ApiModelProperty(name="책 카테고리", example="0" )
    Long bookCategoryId;

    @ApiModelProperty(name="영화 카테고리", example="1" )
    Long movieCategoryId;

    @ApiModelProperty(name="비밀번호", example="1234dfsf" )
    String password;

    @ApiModelProperty(name="이미지url", example="resources/dist/img" )
    String url;

    @ApiModelProperty(name="세션 Id", example="Dfjk_sSBgs_nWdk" )
    String sessionId;
}
