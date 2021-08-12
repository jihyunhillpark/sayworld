package com.ssafy.api.controller;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.CategoryService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.BookCategory;
import com.ssafy.db.entity.MovieCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Api(value = "영화, 책 카테고리 관련 API", tags = {"Category"})
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 영화 카테고리 불러오기 */
    @GetMapping("/movie")
    @ApiOperation(value = "영화 카테고리 불러오기", notes ="저장한 영화 카테고리 리스트를 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public List<MovieCategory> getMovieCategoryList() {
        return categoryService.getMovieCategoryList();
    }

    /**
     * 책 카테고리 불러오기 */
    @GetMapping("/book")
    @ApiOperation(value = "책 카테고리 불러오기", notes ="저장한 책 카테고리 리스트를 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public List<BookCategory> getBookCategoryList(){
        return categoryService.getBookCategoryList();
    }
}
