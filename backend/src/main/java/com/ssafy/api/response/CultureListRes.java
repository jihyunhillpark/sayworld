package com.ssafy.api.response;

import com.ssafy.db.entity.Culture;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CultureListResponse")
public class CultureListRes {
    @ApiModelProperty(name = "문화력 글 번호")
    Long CulturePowerId;
    @ApiModelProperty(name = "Culture Title")
    String cultureTitle;
    @ApiModelProperty(name = "Culture Category")
    int cultureCategory;
    @ApiModelProperty(name = "이메일")
    String userEmail;

    public static CultureListRes create(Culture culture, String email){
        CultureListRes res = new CultureListRes();
        res.setCulturePowerId(culture.getCulturePowerId());
        res.setUserEmail(email);
        res.setCultureTitle(culture.getCultureTitle());
        res.setCultureCategory(culture.getCultureCategory());
        return res;
    }
}
