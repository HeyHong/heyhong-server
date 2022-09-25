package com.heyhong.HeyHong.users.controller;

import com.heyhong.HeyHong.config.exception.UpdateSameElementException;
import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.users.dto.*;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.service.LikeService;
import com.heyhong.HeyHong.users.service.MemberService;
import com.heyhong.HeyHong.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/members")
public class MemberController {

    private final LikeService likeService;
    private final MemberService memberService;
    private final UserService userService;


    /**
     * GET 메인 화면
     * @param user
     * @return
     */
    @ResponseBody
    @GetMapping("/main")
    public ResponseEntity<BaseResponse> retrieveAppMain(@AuthenticationPrincipal Users user){
        try{
            MainRes result = memberService.getMain(user);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, result);
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }
    }

    /**
     * 로그아웃
     * @param user
     * @return
     */
    @ResponseBody
    @PutMapping("/logout")
    public ResponseEntity<BaseResponse> logout(@AuthenticationPrincipal Users user){
        try{
            memberService.logout(user);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }
    }

//    @ResponseBody
//    @DeleteMapping("/secession")
//    public ResponseEntity<BaseResponse> secession(@AuthenticationPrincipal Users user){
//        try{
//            memberService.secession(user);
//            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
//            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
//        }catch (Exception e){
//            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
//            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
//        }
//    }




    /**
     * GET 프로필 메인
     * @param user
     * @return
     */
    @ResponseBody
    @GetMapping("/profile")
    public ResponseEntity<BaseResponse> retrieveProfile(@AuthenticationPrincipal Users user){
        try{
            ProfileDto result = memberService.retrieveUserProfileInfo(user.getId());
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, result);
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }
    }


    /**
     * GET 시설 카테고리 즐겨찾기
     * @param user
     * @param facilityCategoryPk
     * @return
     */
    @ResponseBody
    @PostMapping("/like/facility-category/{facilityCategoryPk}")
    public ResponseEntity<BaseResponse> likeFacilityCategory(@AuthenticationPrincipal Users user, @PathVariable Long facilityCategoryPk){

        try{
            likeService.likeFacilityCategory(user, facilityCategoryPk);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }

    }

    /**
     * DELETE 시설 카테고리 즐겨찾기 취소
     * @param user
     * @param facilityCategoryPk
     * @return
     */
    @ResponseBody
    @DeleteMapping("/like/facility-category/{facilityCategoryPk}")
    public ResponseEntity<BaseResponse> unlikeFacilityCategory(@AuthenticationPrincipal Users user, @PathVariable Long facilityCategoryPk){
        try{
            likeService.unlikeFacilityCategory(user, facilityCategoryPk);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }
    }

    /**
     * 시설 즐겨찾기
     * @param user
     * @param facilityPk
     * @return
     */
    @ResponseBody
    @PostMapping("/like/facility/{facilityPk}")
    public ResponseEntity<BaseResponse> likeFacility(@AuthenticationPrincipal Users user, @PathVariable Long facilityPk){

        try{
            likeService.likeFacility(user, facilityPk);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }

    }


    /**
     * 시설 즐겨찾기 취소
     * @param user
     * @param facilityPk
     * @return
     */
    @ResponseBody
    @DeleteMapping("/like/facility/{facilityPk}")
    public ResponseEntity<BaseResponse> unlikeFacility(@AuthenticationPrincipal Users user, @PathVariable Long facilityPk){
        try{
            likeService.unlikeFacility(user, facilityPk);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }
    }

    /**
     * GET 프로필 - 학부/학과 변
     * @param user
     * @return
     */
    @ResponseBody
    @GetMapping("/profile/dept")
    public ResponseEntity<BaseResponse> readMemberDepartment(@AuthenticationPrincipal Users user){

        try{
            List<CollegeDeptDto> collegeDeptList = userService.getCollegeDeptList();
            GetUpdateMemberDepartmentRes result = new GetUpdateMemberDepartmentRes(user.getCollege().getId(), user.getDepartment().getId(), collegeDeptList);

            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, result);
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }

    }


    /**
     * PATCH 프로필 - 학부/학과 변경
     * @param user
     * @param updateMemberDeptReq
     * @return
     */
    @ResponseBody
    @PatchMapping("/profile/dept")
    public ResponseEntity<BaseResponse> updateMemberDepartment(@AuthenticationPrincipal Users user, @RequestBody UpdateMemberDeptReq updateMemberDeptReq){

        try{
            memberService.updateMemberDepartment(user, user.getCollege().getId(), user.getDepartment().getId(), updateMemberDeptReq.getCollegePk(), updateMemberDeptReq.getDepartmentPk());

            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (UpdateSameElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }

    }


    /**
     * POST 프로필 - 비밀번호 변경
     * @param user
     * @param updateMemberPasswordReq
     * @return
     */
    @ResponseBody
    @PatchMapping ("/profile/password")
    public ResponseEntity<BaseResponse> updateMemberPassword(@AuthenticationPrincipal Users user, @RequestBody UpdateMemberPasswordReq updateMemberPasswordReq){

        try{
            userService.changePassword(user, updateMemberPasswordReq.getCurPassword(), updateMemberPasswordReq.getNewPassword());
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (IllegalArgumentException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }
    }



}
