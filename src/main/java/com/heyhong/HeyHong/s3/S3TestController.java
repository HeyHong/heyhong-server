package com.heyhong.HeyHong.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class S3TestController {

    private final S3Uploader s3Uploader;

    @PostMapping("/s3images")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException{
        String imageUrl = s3Uploader.upload(multipartFile, "static");
        System.out.println("imageUrl : " + imageUrl);
        return imageUrl;
    }
}
