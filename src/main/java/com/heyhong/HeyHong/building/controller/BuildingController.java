package com.heyhong.HeyHong.building.controller;

import com.heyhong.HeyHong.building.dto.RetrieveFloorRes;
import com.heyhong.HeyHong.building.service.BuildingService;
import com.heyhong.HeyHong.config.response.BaseResponse;
import com.heyhong.HeyHong.config.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/app/building")
public class BuildingController {

    private final BuildingService buildingService;

    /**
     * GET 건물 층 도면 조회
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/floor/{id}")
    public ResponseEntity<BaseResponse> retrieveFloor(@PathVariable Long id){
        try{
            RetrieveFloorRes result = buildingService.retrieveFloorMapImageUrl(id);
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
}
