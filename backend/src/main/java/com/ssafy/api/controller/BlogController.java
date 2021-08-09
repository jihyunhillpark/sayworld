package com.ssafy.api.controller;


import com.ssafy.api.request.BlogDeleteReq;
import com.ssafy.api.response.BlogReqRes;
import com.ssafy.api.service.BlogService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "유저 블로그 Api", tags = {"Blog"})
@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/{userEmail}")
    @ApiOperation(value = "회원 블로그 글 목록 조회", notes = "사용자의 블로그 글 목록 조회")
    @ApiResponses({
            @ApiResponse(code=200, message = "성공"),
            @ApiResponse(code=400, message = "인증 실패"),
            @ApiResponse(code=401, message = "사용자 없음"),
            @ApiResponse(code=500, message = "서버오류")
    })
    public ResponseEntity<List<BlogReqRes>> getBlogList(@PathVariable String userEmail){

        return new ResponseEntity(blogService.getBlogList(userEmail), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "회원 블로그 글 작성", notes = "사용자의 블로그 목록에 새로운 글 작성")
    @ApiResponses({
            @ApiResponse(code=200, message = "성공"),
            @ApiResponse(code=400, message = "인증 실패"),
            @ApiResponse(code=401, message = "사용자 없음"),
            @ApiResponse(code=500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponseBody> postBlog(@RequestBody @ApiParam(value = "블로그 글 작성", required = true) BlogReqRes blogReqRes){
        blogService.postBlog(blogReqRes);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PutMapping
    @ApiOperation(value = "회원 블로그 글 수정", notes = "사용자의 블로그 목록에 기존 글 수정")
    @ApiResponses({
            @ApiResponse(code=200, message = "성공"),
            @ApiResponse(code=400, message = "인증 실패"),
            @ApiResponse(code=401, message = "사용자 없음"),
            @ApiResponse(code=500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponseBody> fixBlog(@RequestBody @ApiParam(value = "블로그 글 수정", required = true) BlogReqRes blogReqRes){
        blogService.fixBlog(blogReqRes);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping
    @ApiOperation(value = "회원 블로그 글 삭제", notes = "사용자의 블로그 목록에 기존 글 삭제")
    @ApiResponses({
            @ApiResponse(code=200, message = "성공"),
            @ApiResponse(code=400, message = "인증 실패"),
            @ApiResponse(code=401, message = "사용자 없음"),
            @ApiResponse(code=500, message = "서버오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteBlog(@RequestBody @ApiParam(value = "블로그 글 삭제", required = true) BlogDeleteReq blogDeleteReq){
        blogService.deleteBlog(blogDeleteReq.getEmail(), blogDeleteReq.getBlogId());

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
