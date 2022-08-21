package com.heyhong.HeyHong.s3;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.entity.FacilityImage;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import com.heyhong.HeyHong.facility.repository.FacilityImageRepository;
import com.heyhong.HeyHong.facility.repository.FacilityRepository;
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
    private final FacilityRepository facilityRepository;
    private final FacilityImageRepository facilityImageRepository;


    @PostMapping("/app/users/s3images")
    public String uploadRelatedUsers(@RequestParam("images") MultipartFile multipartFile, @RequestParam String object, @RequestParam Long id) throws IOException{

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

    @PostMapping("/app/users/facility/s3images")
    public String uploadRelatedFacility(@RequestParam("images") MultipartFile multipartFile, @RequestParam String object, @RequestParam Long id) throws IOException{

        if(object.equals("시설이미지")){

            Optional<Facility> f = facilityRepository.findById(id);

            //파일 압축 필요

            String imageUrl = s3Uploader.upload(multipartFile, "facility_image");
            FacilityImage facilityImage = new FacilityImage(f.get(), imageUrl);
            facilityImageRepository.save(facilityImage);

            return imageUrl;
        }
        else{
            return "해당되지 않는 업로드";
        }

    }
}
