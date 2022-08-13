package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.dto.FacilityCommentCountDto;
import com.heyhong.HeyHong.facility.entity.Facility;

import java.util.ArrayList;
import java.util.List;

public interface QFacilityRepository {

    List<FacilityCommentCountDto> findFacilityCommentCount(ArrayList<Long> ids);
    List<Facility> findAllByFacilityCategoryOrderById(Long facilityCategoryId);
}
