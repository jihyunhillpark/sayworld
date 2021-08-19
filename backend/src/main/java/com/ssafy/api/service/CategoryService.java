package com.ssafy.api.service;

import com.ssafy.db.entity.BookCategory;
import com.ssafy.db.entity.MovieCategory;

import java.util.List;

public interface CategoryService {

    public List<MovieCategory> getMovieCategoryList();
    public List<BookCategory> getBookCategoryList();
}
