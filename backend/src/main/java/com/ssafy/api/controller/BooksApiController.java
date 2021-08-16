package com.ssafy.api.controller;

import com.ssafy.api.response.BooksApiPostRes;
import com.ssafy.api.service.BooksService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Api(value = "북 api", tags = {"Books"})
@RestController
@RequestMapping("/api/v1/books")
public class BooksApiController {
    @Autowired
    BooksService booksService;

    @PostMapping("/search")
    public Mono<BooksApiPostRes> findBookByQuery(@RequestBody Map<String, Map> title) {

        Object keyword = title.get("params").get("title");
        //System.out.println(keyword);
        //HashMap<String, Object> resultMap = new HashMap<>();
        String result = String.valueOf(keyword);

        //System.out.println("되냐?");
        //System.out.println(booksService.findBookByQuery((result)));
        return booksService.findBookByQuery(result);
    }

}