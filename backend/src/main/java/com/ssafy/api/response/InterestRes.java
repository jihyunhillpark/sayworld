package com.ssafy.api.response;

import com.ssafy.db.entity.BookCategory;
import com.ssafy.db.entity.MovieCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestRes {
    Long categoryId;
    String category;
    int movieOrBook;

    public static InterestRes createBook(BookCategory books){
        InterestRes res = new InterestRes();
        res.setCategoryId(books.getBookCategoryId());
        res.setCategory(books.getBookCategory());
        res.setMovieOrBook(1);
        return res;
    }

    public static InterestRes createMovie(MovieCategory movies){
        InterestRes res = new InterestRes();
        res.setCategoryId(movies.getMovieCategoryId());
        res.setCategory(movies.getMovieCategory());
        res.setMovieOrBook(0);
        return res;
    }
}
