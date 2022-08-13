package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityCategoryRepository extends JpaRepository<FacilityCategory, Long>, QFacilityCategoryRepository {

}
