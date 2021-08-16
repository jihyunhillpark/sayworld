package com.ssafy.api.service;

import com.ssafy.api.response.MoviesApiPostRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovieServiceImpl implements MovieService{
    @Value("https://openapi.naver.com/v1/search/movie.json")
    private String naverOpenApiUrl;

    @Value("${naver.openapi.client.id}")
    private String naverOpenApiClientId;

    @Value("${naver.openapi.client.secret}")
    private String naverOpenApiClientSecret;


    @Override
    public Mono<MoviesApiPostRes> findMovieByQuery(String query) {

        return WebClient.create(naverOpenApiUrl)
                .method(HttpMethod.GET)
                .uri("?query=" + query)
                .header("X-Naver-Client-Id", "fj19qSeYZpP5xmRuUQ_l")
                .header("X-Naver-Client-Secret", "ZeN6POhLUU")
                .retrieve()
                .bodyToMono(MoviesApiPostRes.class);
    }
}
