package com.heyhong.HeyHong.users.controller;

import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.users.dto.LikeFacilityCategoryReq;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/members")
public class MemberController {

    private final LikeService likeService;

    /**
     * 시설 카테고리 즐겨찾기
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
     * 시설 카테고리 즐겨찾기 취소
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
}
