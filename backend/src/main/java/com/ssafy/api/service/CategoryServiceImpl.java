package com.ssafy.api.service;

import com.ssafy.db.entity.BookCategory;
import com.ssafy.db.entity.MovieCategory;
import com.ssafy.db.repository.BookCategoryRepository;
import com.ssafy.db.repository.MovieCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    MovieCategoryRepository movieCategoryRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Override
    public List<MovieCategory> getMovieCategoryList() {
        return movieCategoryRepository.findAll();
    }

    @Override
    public List<BookCategory> getBookCategoryList() {
        return bookCategoryRepository.findAll();
    }
}
