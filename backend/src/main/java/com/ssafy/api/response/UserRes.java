package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	@ApiModelProperty(name = "user ID PK")
	Long userId;
	@ApiModelProperty(name="User ID")
	String email;
	@ApiModelProperty(name="User Nickname")
	String nickname;
	@ApiModelProperty(name="User gender")
	String gender;
	@ApiModelProperty(name="User age")
	int age;
	@ApiModelProperty(name="User default Page")
	int defaultPage;
	@ApiModelProperty(name="User book tier")
	int tierBook;
	@ApiModelProperty(name="User movie tier")
	int tierMovie;
	@ApiModelProperty(name="User profile img")
	String profileImg;



	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUserId(user.getUserId());
		res.setEmail(user.getEmail());
		res.setNickname(user.getNickname());
		res.setAge(user.getAge());
		res.setDefaultPage(user.getDefaultPage());
		res.setTierBook(user.getTierBook());
		res.setTierMovie(user.getTierMovie());
		res.setProfileImg(user.getProfileImg());

		return res;
	}
}
