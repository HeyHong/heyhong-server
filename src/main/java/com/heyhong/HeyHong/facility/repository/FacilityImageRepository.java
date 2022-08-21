package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityImageRepository extends JpaRepository<FacilityImage, Long> {

    List<FacilityImage> findByFacilityAndStatus(Facility facility, FacilityImage.Status status);
}
