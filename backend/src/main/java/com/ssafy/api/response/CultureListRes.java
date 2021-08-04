package com.ssafy.api.response;

import com.ssafy.db.entity.Culture;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CultureListResponse")
public class CultureListRes {
    @ApiModelProperty(name = "Culture Title")
    String title;
    @ApiModelProperty(name = "Culture Category")
    char category;

    public static CultureListRes create(Culture culture){
        CultureListRes res = new CultureListRes();
        res.setTitle(culture.getCultureTitle());
        res.setCategory(culture.getCultureCategory());
        return res;
    }
}
