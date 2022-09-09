package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.dto.FacilityCategoryDao;
import com.heyhong.HeyHong.facility.dto.LikedFacilityCategoryDao;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.users.entity.Users;

import java.util.List;

public interface QFacilityCategoryRepository {

    List<Facility> findFacilityAll();
    List<FacilityCategoryDao> findFacilityCategoryListAll(Users user);
    List<LikedFacilityCategoryDao> findAllLikedFacilityCategory(Users user);

}
