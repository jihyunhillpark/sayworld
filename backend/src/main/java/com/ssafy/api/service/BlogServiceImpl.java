package com.ssafy.api.service;

import com.ssafy.api.response.BlogReqRes;
import com.ssafy.db.entity.Blog;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BlogRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<BlogReqRes> getBlogList(String email){
        User user = userRepository.findByEmail(email).get();
        List<Blog> blogList = user.getBlogs();

        List<BlogReqRes> resList = new ArrayList<>();
        for(Blog blog: blogList){
            resList.add(BlogReqRes.create(blog, email));
        }

        return resList;
    }

    @Override
    public void postBlog(BlogReqRes blogReq){
        String myEmail = blogReq.getUserEmail();
        User user = userRepository.findByEmail(myEmail).get();

        List<Blog> blogList = user.getBlogs();

        Blog blog = new Blog();
        blog.setUser(user);
        blog.setBlogCategory(blogReq.getBlogCategory());
        blog.setBlogContent(blogReq.getBlogContent());
        blog.setBlogCreatedAt(blogReq.getBlogCreatedAt());
        blog.setBlogLock(blogReq.getBlogLock());
        blog.setBlogTitle(blogReq.getBlogTitle());

        blogList.add(blog);

        userRepository.save(user);
    }

    @Override
    public void fixBlog(BlogReqRes blogReq){
        Blog blog = blogRepository.findByBlogId(blogReq.getBlogId()).get();

        blog.setBlogCategory(blogReq.getBlogCategory());
        blog.setBlogContent(blogReq.getBlogContent());
        blog.setBlogCreatedAt(blogReq.getBlogCreatedAt());
        blog.setBlogLock(blogReq.getBlogLock());
        blog.setBlogTitle(blogReq.getBlogTitle());

        blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(String myEmail, long blogId){
        User user = userRepository.findByEmail(myEmail).get();
        List<Blog> blogList = user.getBlogs();

        blogList.remove(blogRepository.findByBlogId(blogId).get());
        //blogRepository.deleteById(blogId);
        userRepository.save(user);
    }
}
