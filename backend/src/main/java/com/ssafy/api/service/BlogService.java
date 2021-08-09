package com.ssafy.api.service;

import com.ssafy.api.response.BlogReqRes;

import java.util.List;

public interface BlogService {
    List<BlogReqRes> getBlogList(String email);
    void postBlog(BlogReqRes blogReqRes);
    void fixBlog(BlogReqRes blogReqRes);
    void deleteBlog(String email, long blogId);
}
