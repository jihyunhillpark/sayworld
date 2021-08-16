package com.ssafy.api.service;


import com.ssafy.api.response.MoviesApiPostRes;
import reactor.core.publisher.Mono;

public interface MovieService {
    Mono<MoviesApiPostRes> findMovieByQuery(String query);
}
