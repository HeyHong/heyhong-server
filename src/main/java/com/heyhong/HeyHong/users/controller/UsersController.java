package com.heyhong.HeyHong.users.controller;

import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.users.dto.LoginReq;
import com.heyhong.HeyHong.users.dto.SignInReq;
import com.heyhong.HeyHong.users.dto.SignInRes;
import com.heyhong.HeyHong.users.jwt.JwtTokenProvider;
import com.heyhong.HeyHong.users.service.AuthService;
import com.heyhong.HeyHong.users.service.UserService;
import com.heyhong.HeyHong.users.service.UsersAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final AuthService authService;


    @ResponseBody
    @PostMapping("/sign-in")
    public ResponseEntity<BaseResponse> signIn(@RequestBody SignInReq req){
        System.out.println("here");
        try{
            Long userPk = userService.signIn(req.getUserId(), req.getPassword(), req.getNickname(), req.getStudentId(), req.getEmail(), req.getCollege(), req.getDepartment());
//            return new BaseResponse<>(BaseResponseStatus.OK, new SignInRes(userPk, userService.login(req.getUserId(), req.getPassword())));
            LoginReq tmpLoginReq = new LoginReq(req.getUserId(), req.getPassword());
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, new SignInRes(userPk, authService.login(tmpLoginReq)) );
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }
    }

//    @ResponseBody
//    @GetMapping("/hi")
//    public ResponseEntity<SignInRes> test(){
//        System.out.println("hello");
//        return new ResponseEntity<SignInRes>(new SignInRes(1L, "sdfad"), HttpStatus.OK);
//    }

}
