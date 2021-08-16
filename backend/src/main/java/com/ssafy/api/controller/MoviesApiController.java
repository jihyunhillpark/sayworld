package com.ssafy.api.controller;


import com.ssafy.api.response.MoviesApiPostRes;
import com.ssafy.api.service.BooksService;
import com.ssafy.api.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@Api(value = "영화 api", tags = {"Movies"})
@RestController
@RequestMapping("/api/v1/movies")
public class MoviesApiController {
    @Autowired
    MovieService movieService;

    @PostMapping("/search")
    public Mono<MoviesApiPostRes> findMovieByQuery(@RequestBody Map<String, Map> title) {

        Object keyword = title.get("params").get("title");
        System.out.println(keyword);
        //HashMap<String, Object> resultMap = new HashMap<>();
        String result = String.valueOf(keyword);

        //System.out.println("되냐?");
        return movieService.findMovieByQuery(result);
    }

}