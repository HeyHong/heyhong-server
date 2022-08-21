package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long>, QFacilityRepository {

    Optional<Facility> findByIdAndStatus(Long facilityId, Facility.Status status);
    List<Facility> findByParent(Facility parent);
}
