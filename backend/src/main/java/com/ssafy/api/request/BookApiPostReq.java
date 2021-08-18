package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BookApiRequest")
public class BookApiPostReq {
    @ApiModelProperty(name="책이름", example="불곰의")
    String title;
    @ApiModelProperty(name="이미지", example="http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902")
    String image;
    @ApiModelProperty(name="저자", example="불곰 박선목")
    String author;
    @ApiModelProperty(name="요약본", example="책 간단 요약 샬라샬라")
    String description;

}
