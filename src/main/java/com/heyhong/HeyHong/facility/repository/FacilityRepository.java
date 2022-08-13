package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long>, QFacilityRepository {

}
