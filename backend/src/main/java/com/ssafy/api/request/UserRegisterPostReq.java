package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {

	@ApiModelProperty(name="유저 ID(email)", example="helloculsu@naver.com")
	String email;
	@ApiModelProperty(name="유저 Password", example="culsu1004!")
	String password;
	@ApiModelProperty(name="유저 별명", example="철수철수")
	String nickname;
	@ApiModelProperty(name="유저 성별", example="M")
	String gender;
	@ApiModelProperty(name="유저 나이", example="18")
	int age;
	@ApiModelProperty(name="첫 시작 화면", example="0")
	int defaultPage;
	@ApiModelProperty(name="유저의 책문화력 티어", example="0")
	int tierBook;
	@ApiModelProperty(name="유저의 영화문화력 티어", example="0")
	int tierMovie;
	@ApiModelProperty(name="유저 프로필사진", example="/url")
	String profileImg;
}
