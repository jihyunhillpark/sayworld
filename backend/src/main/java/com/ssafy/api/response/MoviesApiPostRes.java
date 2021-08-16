package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ApiModel("MoviesApiGetResponse")
public class MoviesApiPostRes implements Serializable {

    private int display;
    private List<BooksApiPostRes.naverDocument> items;

    @Data
    public static class naverDocument {
        @ApiModelProperty(name = "영화이름", example = "모가디슈")
        String title;
        @ApiModelProperty(name = "이미지", example = "http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902")
        String image;
        @ApiModelProperty(name = "작가", example = "류승완")
        String director;
        @ApiModelProperty(name = "평점", example = "사용자 평점")
        String userRating;
    }
}