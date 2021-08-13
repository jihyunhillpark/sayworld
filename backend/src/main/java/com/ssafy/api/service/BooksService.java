package com.ssafy.api.service;

import com.ssafy.api.response.BooksApiPostRes;
import reactor.core.publisher.Mono;

public interface BooksService {
    Mono<BooksApiPostRes> findBookByQuery(String query);
}
