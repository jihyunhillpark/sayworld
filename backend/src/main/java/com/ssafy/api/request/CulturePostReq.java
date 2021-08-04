package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserCulturePostRequest")
public class CulturePostReq {

    @ApiModelProperty(name = "책(B) 또는 영화(M)", example = "B")
    char cultureCategory;

    @ApiModelProperty(name = "책/영화 제목", example = "7년의 밤")
    String cultureTitle;
}
