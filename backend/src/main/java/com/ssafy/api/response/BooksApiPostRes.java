package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("BooksApiGetResponse")
public class BooksApiPostRes implements Serializable {

    private int display;
    private List<naverDocument> items;
    @Data
    public static class naverDocument {
        @ApiModelProperty(name="책이름", example="불곰의")
        String title;
        @ApiModelProperty(name="이미지", example="http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902")
        String image;
        @ApiModelProperty(name="책이름", example="불곰 박선목")
        String author;
        @ApiModelProperty(name="책이름", example="부키")
        String publisher;
    }

    //이제 여기부분에 받는부분 코드 짜보면됨.

}
