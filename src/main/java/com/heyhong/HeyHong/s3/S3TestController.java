package com.heyhong.HeyHong.s3;

import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class S3TestController {

    private final S3Uploader s3Uploader;
    private final FacilityCategoryRepository facilityCategoryRepository;


    @PostMapping("/app/users/s3images")
    public String upload(@RequestParam("images") MultipartFile multipartFile, @RequestParam String object, @RequestParam Long id) throws IOException{

        if(object.equals("시설카테고리")){

            Optional<FacilityCategory> fc = facilityCategoryRepository.findById(id);
            String imageUrl = s3Uploader.upload(multipartFile, "facility_category");
            fc.get().setImage(imageUrl);
            facilityCategoryRepository.save(fc.get());

            return imageUrl;
        }
        else{
            return "해당되지 않는 업로드";
        }

    }
}
