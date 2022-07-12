package com.heyhong.HeyHong.users.controller;


import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.users.dto.LoginReq;
import com.heyhong.HeyHong.users.dto.LoginRes;
import com.heyhong.HeyHong.users.dto.UpdateAccessTokenReq;
import com.heyhong.HeyHong.users.dto.UpdateAccessTokenRes;
import com.heyhong.HeyHong.users.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/auth")
public class AuthController {

    private final AuthService authService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginReq loginReq){
        System.out.println("--login controller--");

        try {
            LoginRes loginRes = authService.login(loginReq);

            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, loginRes);
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
        }
        catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.UNAUTHORIZED, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.UNAUTHORIZED.getCode());
        }

    }

    @ResponseBody
    @PostMapping("/refreshToken")
    public ResponseEntity<BaseResponse> updateRefreshToken(@RequestBody UpdateAccessTokenReq updateAccessTokenReq, HttpServletRequest request){
        System.out.println("--updateRefresthToken controller--");

        try {
            UpdateAccessTokenRes updateAccessTokenRes = authService.newAccessToken(updateAccessTokenReq, request);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, updateAccessTokenRes);
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<BaseResponse>(new BaseResponse(BaseResponseStatus.RELOGIN), BaseResponseStatus.RELOGIN.getCode());
        }

    }

}
