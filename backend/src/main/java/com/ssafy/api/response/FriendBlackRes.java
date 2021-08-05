package com.ssafy.api.response;


import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FriendBlackRes")
public class FriendBlackRes {
    @ApiModelProperty(name = "user Id")
    Long userId;
    @ApiModelProperty(name = "user email")
    String email;
    @ApiModelProperty(name = "user nickname")
    String nickname;
    @ApiModelProperty(name = "user profile image url")
    String profileImg;
    @ApiModelProperty(name = "user book tier")
    int tierBook;
    @ApiModelProperty(name = "user movie tier")
    int tierMovie;

    public static FriendBlackRes create(User user){
        FriendBlackRes res = new FriendBlackRes();
        res.setUserId(user.getUserId());
        res.setEmail(user.getEmail());
        res.setNickname(user.getNickname());
        res.setProfileImg(user.getProfileImg());
        res.setTierBook(user.getTierBook());
        res.setTierMovie(user.getTierMovie());
        return res;
    }
}
