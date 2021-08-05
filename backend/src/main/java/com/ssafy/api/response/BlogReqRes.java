package com.ssafy.api.response;

import com.ssafy.db.entity.Blog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("블로그 글 형식")
public class BlogReqRes {
    @ApiModelProperty(name = "블로그 글 번호")
    Long blogId;
    @ApiModelProperty(name = "블로그 글 제목")
    String blogTitle;
    @ApiModelProperty(name = "블로그 글쓴이 이메일")
    String userEmail;
    @ApiModelProperty(name = "블로그 글 내용")
    String blogContent;
    @ApiModelProperty(name = "블로그 글생성 날짜")
    Date blogCreatedAt;
    @ApiModelProperty(name = "블로그 글 공개여부")
    int blogLock;
    @ApiModelProperty(name = "블로그 글 카테고리")
    int blogCategory;

    public static BlogReqRes create(Blog blog, String email){
        BlogReqRes res = new BlogReqRes();
        res.setBlogId(blog.getBlogId());
        res.setBlogTitle(blog.getBlogTitle());
        res.setUserEmail(email);
        res.setBlogContent(blog.getBlogContent());
        res.setBlogCreatedAt(blog.getBlogCreatedAt());
        res.setBlogLock(blog.getBlogLock());
        res.setBlogCategory(blog.getBlogCategory());

        return res;
    }
}
