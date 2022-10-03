package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.facility.dto.LikedFacilityCategoryDao;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.users.entity.LikeFacility;
import com.heyhong.HeyHong.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeFacilityRepository extends JpaRepository<LikeFacility, Long> {

    Boolean existsByUserAndFacilityAndStatus(Users user, Facility facility, LikeFacility.Status status);
    Optional<LikeFacility> findByUserAndFacility(Users user, Facility facility);
    List<LikeFacility> findAllByUser(Users user);

}
