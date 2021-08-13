package com.ssafy.api.controller;

import com.ssafy.api.request.BookApiPostReq;
import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.response.BooksApiPostRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.BooksService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(value = "북 api", tags = {"Books"})
@RestController
@RequestMapping("/api/v1/books")
public class BooksApiController {
    @Autowired
    BooksService booksService;

    @PostMapping("book")
    public Mono<BooksApiPostRes> findBookByQuery() {
        return booksService.findBookByQuery("스프링부트");
    }


    @PostMapping("/check")
    @ApiOperation(value = "책 검색값 받기", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> check(@RequestBody @ApiParam(value = "로그인 정보", required = true) BooksApiPostRes inputInfo, HttpServletResponse res) {
        String title = inputInfo.toString(); //아직 res부분 덜 마무리..
        System.out.println(title);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}