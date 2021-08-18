package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MovieApiRequest")
public class MovieApiPostReq {
    @ApiModelProperty(name="영화이름", example="모가디슈")
    String title;
    @ApiModelProperty(name="이미지", example="http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902")
    String image;
    @ApiModelProperty(name="감독", example="류승완")
    String director;
    @ApiModelProperty(name="배우", example="김윤석")
    String actor;
    @ApiModelProperty(name="평점", example="사용자 평점")
    String userRating;

}
