package com.heyhong.HeyHong.facility.controller;


import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/facility")
public class FacilityController {

    @ResponseBody
    @GetMapping("/test")
    public ResponseEntity<BaseResponse> test(@AuthenticationPrincipal Users user){
        System.out.println("------controller------");
        System.out.println(user.getUserId());

        BaseResponse res = new BaseResponse(BaseResponseStatus.OK, new UpdateAccessTokenRes("sd", 1L));
        return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
    }
}
