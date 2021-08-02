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
	@ApiModelProperty(name="유저 Password", example="철수철수")
	String nickname;
	@ApiModelProperty(name="유저 Password", example="M")
	String gender;
	@ApiModelProperty(name="유저 Password", example="18")
	int age;
	@ApiModelProperty(name="유저 Password", example="B")
	String defaultPage;
	@ApiModelProperty(name="유저 Password", example="0")
	int tierBook;
	@ApiModelProperty(name="유저 Password", example="0")
	int tierMovie;
	@ApiModelProperty(name="유저 Password", example="/url")
	String profileImg;
}
