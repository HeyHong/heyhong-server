package com.heyhong.HeyHong.users.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.users.dto.*;
import com.heyhong.HeyHong.users.jwt.JwtTokenProvider;
import com.heyhong.HeyHong.users.service.AuthService;
import com.heyhong.HeyHong.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final AuthService authService;

    /**
     * 회원가입 최종
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-in")
    public ResponseEntity<BaseResponse> signIn(@RequestBody SignInReq req){
        System.out.println("here");
        try{
            Long userPk = userService.signIn(req.getUserId(), req.getPassword(), req.getNickname(), req.getEmail(), req.getCollegePk(), req.getDepartmentPk());
//            return new BaseResponse<>(BaseResponseStatus.OK, new SignInRes(userPk, userService.login(req.getUserId(), req.getPassword())));
            LoginReq tmpLoginReq = new LoginReq(req.getUserId(), req.getPassword());
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, new SignInRes(userPk, authService.login(tmpLoginReq)) );
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }
    }


    /**
     * 아이디 중복 체크
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-in/validate-userId")
    public ResponseEntity<BaseResponse> validateUserId(@RequestBody ValidateUserIdReq req){

        if(userService.validateUserId(req.getUserId())){
            BaseResponse res = new BaseResponse(BaseResponseStatus.VALID_USERID, BaseResponseStatus.VALID_USERID.getMessage() );
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.VALID_USERID.getCode());
        }else{
            BaseResponse res = new BaseResponse(BaseResponseStatus.DUPLICATE_USER_ID, BaseResponseStatus.DUPLICATE_USER_ID.getMessage() );
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.DUPLICATE_USER_ID.getCode());
        }
    }

    /**
     * 닉네임 중복 체크
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-in/validate-nickname")
    public ResponseEntity<BaseResponse> validateNickname(@RequestBody ValidateNicknameReq req){

        if(userService.validateNickname(req.getNickname())){
            BaseResponse res = new BaseResponse(BaseResponseStatus.VALID_NICKNAME, BaseResponseStatus.VALID_NICKNAME.getMessage() );
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.VALID_NICKNAME.getCode());
        }else{
            BaseResponse res = new BaseResponse(BaseResponseStatus.DUPLICATE_NICKNAME, BaseResponseStatus.DUPLICATE_NICKNAME.getMessage() );
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.DUPLICATE_NICKNAME.getCode());
        }
    }


    /**
     * 회원가입 - 이메일 인증 STEP1 전송
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-in/confirm/email")
    public ResponseEntity<BaseResponse> sendConfirmEmail(@RequestBody SendConfirmEmailReq req){

        try{
            Long confirmationTokenPk = userService.createEmailConfirmation(req.getEmail());

            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, new SendConfirmEmailRes(confirmationTokenPk));
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
        }catch (IllegalArgumentException e){

            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, "학교 이메일이 아닙니다.");
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());

        }catch (Exception e){

            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }

    }


    /**
     * 회원가입 - 이메일 인증 STEP2 check
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-in/confirm/email/check")
    public ResponseEntity<BaseResponse> checkConfirmEmail(@RequestBody CheckConfirmEmailReq req){

        try{
            if(userService.checkConfirmEmail(req.getConfirmPk(), req.getEmail(), req.getConfirmCode())){
                BaseResponse res = new BaseResponse(BaseResponseStatus.OK, "인증에 성공하였습니다");
                return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
            }else{
                BaseResponse res = new BaseResponse(BaseResponseStatus.UNAUTHORIZED, "인증번호가 옳지 않습니다");
                return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.UNAUTHORIZED.getCode());
            }
        }catch (Exception e){

            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());

        }


    }


    /**
     * 회원가입 - 학부/학과 선택
     * @return
     */
    @ResponseBody
    @GetMapping("/sign-in/college-dept")
    public ResponseEntity<BaseResponse> getCollegeDeptList(){


        try{
            List<CollegeDeptDto> result = userService.getCollegeDeptList();

            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, result);
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());

        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }


    }


}
