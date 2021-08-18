package com.ssafy.api.controller;

import com.ssafy.common.util.S3Uploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/images")
@Api(value = "이미지 API", tags = {"Image"})
public class HelloController {

    private final S3Uploader s3Uploader;

    @PostMapping
    @ApiOperation(value = "이미지 업로드", notes = "이미지 업로드/ 얻어온다.")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile, "static");
        return "test";
    }
}