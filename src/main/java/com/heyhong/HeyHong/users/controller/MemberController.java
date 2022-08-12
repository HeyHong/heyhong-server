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
     * 시설 카테고리 좋아요
     * @param user
     * @param likeFacilityCategoryReq
     * @return
     */
    @ResponseBody
    @PostMapping("/like/facility-category")
    public ResponseEntity<BaseResponse> likeFacilityCategory(@AuthenticationPrincipal Users user, @RequestBody LikeFacilityCategoryReq likeFacilityCategoryReq){

        try{
            likeService.likeFacilityCategory(user.getId(), likeFacilityCategoryReq.getFacilityCategoryPk());
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, BaseResponseStatus.OK.getMessage());
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, "존재하지 않는 시설 카테고리 입니다.");
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.DUPLICATE_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.DUPLICATE_ERROR.getCode());
        }

    }
}
