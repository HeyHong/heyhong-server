package com.heyhong.HeyHong.facility.controller;


import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.facility.dto.FaciltyCategoryGroupDto;
import com.heyhong.HeyHong.facility.service.FacilityService;
import com.heyhong.HeyHong.users.dto.SignInRes;
import com.heyhong.HeyHong.users.dto.UpdateAccessTokenRes;
import com.heyhong.HeyHong.users.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @ResponseBody
    @GetMapping("/category")
    public ResponseEntity<BaseResponse> test(@AuthenticationPrincipal Users user){
        System.out.println("------controller------");
        System.out.println(user.getUserId());

        try {
            List<FaciltyCategoryGroupDto> result = facilityService.getFacilityCategory();

            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, result);
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());

        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }


    }
}
