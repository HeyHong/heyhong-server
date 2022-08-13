package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.Facility;

import java.util.List;

public interface QFacilityCategoryRepository {

    List<Facility> findFacilityAll();

}
