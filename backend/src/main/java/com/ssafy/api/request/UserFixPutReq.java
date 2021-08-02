package com.ssafy.api.request;

/**
 * 유저 정보 수정 API ([PUT] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFixPutRequest")
public class UserFixPutReq {

    @ApiModelProperty(name = "본인 이메일", example = "helloculsu@naver.com")
    String email;
    @ApiModelProperty(name = "바꾸는 비밀번호", example = "12345")
    String password;
    @ApiModelProperty(name = "바꾸는 별명", example = "영의정영희")
    String nickname;
    @ApiModelProperty(name = "바꾸는 성별", example = "F")
    String gender;
    @ApiModelProperty(name = "바꾸는 나이", example = "17")
    int age;
    @ApiModelProperty(name = "바꾼 첫 시작 화면", example = "B")
    String defaultPage;
    @ApiModelProperty(name = "바꾸는 프로필사진 경로", example = "/url2")
    String profileImg;

}
