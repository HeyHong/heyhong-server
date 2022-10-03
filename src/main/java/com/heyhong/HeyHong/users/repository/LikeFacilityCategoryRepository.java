package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.users.entity.LikeFacilityCategory;
import com.heyhong.HeyHong.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeFacilityCategoryRepository extends JpaRepository<LikeFacilityCategory, Long> {
    Optional<LikeFacilityCategory> findByUserAndFacilityCategory(Users user, FacilityCategory facilityCategory);
    boolean existsByUserAndFacilityCategory(Users user, FacilityCategory facilityCategory);
    List<LikeFacilityCategory> findAllByUser(Users user);
}
