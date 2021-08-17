package com.ssafy.api.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.db.entity.Culture;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("UserCulturePostRequest")
public class CulturePostReq {

    @ApiModelProperty(name = "책(1) 또는 영화(0)", example = "0")
    Long cultureCategory;

    @ApiModelProperty(name = "책/영화 제목", example = "7년의 밤")
    String cultureTitle;
}
