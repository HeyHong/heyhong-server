package com.heyhong.HeyHong.facility.controller;


import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import com.heyhong.HeyHong.facility.dto.CreateFacilityCommentReq;
import com.heyhong.HeyHong.facility.dto.FacilityDetailRes;
import com.heyhong.HeyHong.facility.dto.FacilityListItemDto;
import com.heyhong.HeyHong.facility.dto.FaciltyCategoryGroupDto;
import com.heyhong.HeyHong.facility.service.FacilityService;
import com.heyhong.HeyHong.users.dto.SignInRes;
import com.heyhong.HeyHong.users.dto.UpdateAccessTokenRes;
import com.heyhong.HeyHong.users.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/facility")
public class FacilityController {

    private final FacilityService facilityService;

    /**
     * 시설 카테고리 리스트 Controller
     * @param user
     * @return
     */
    @ResponseBody
    @GetMapping("/category")
    public ResponseEntity<BaseResponse> getFacilityCategoryList(@AuthenticationPrincipal Users user){
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

    /**
     * 카테고리에 따른 시설 리스트 Controller
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/category/{id}")
    public ResponseEntity<BaseResponse> getFacilityListByCategory(@PathVariable Long id){

        try{
            List<FacilityListItemDto> result = facilityService.getFacilityList(id);

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
     * 시설 상세 조회 Controller
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getFacilityDetail(@AuthenticationPrincipal Users user, @PathVariable Long id){

        try{
            FacilityDetailRes result = facilityService.readFacilityDetail(id, user);
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK, result);
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.OK.getCode());
        }catch (NoSuchElementException e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.SERVER_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.SERVER_ERROR.getCode());
        }

    }


    /**
     * 시설 댓글 작성 Controller
     * @param user
     * @param createFacilityCommentReq
     * @return
     */
    @ResponseBody
    @PostMapping("/comment")
    public ResponseEntity<BaseResponse> createFacilityComment(@AuthenticationPrincipal Users user, @RequestBody CreateFacilityCommentReq createFacilityCommentReq){

        try{
            facilityService.createFacilityComment(createFacilityCommentReq.getFacilityPk(), createFacilityCommentReq.getContents(), user, createFacilityCommentReq.getReplyCommentPk());
            BaseResponse res = new BaseResponse(BaseResponseStatus.OK);
            return new ResponseEntity<>(res, BaseResponseStatus.OK.getCode());
        }catch (Exception e){
            BaseResponse res = new BaseResponse(BaseResponseStatus.REQUEST_ERROR, e.getMessage());
            return new ResponseEntity<BaseResponse>(res, BaseResponseStatus.REQUEST_ERROR.getCode());
        }

    }
}
