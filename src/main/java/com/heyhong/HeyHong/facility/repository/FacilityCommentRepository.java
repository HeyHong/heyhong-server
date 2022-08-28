package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityCommentRepository extends JpaRepository<FacilityComment, Long> {

    List<FacilityComment> findAllByFacilityOrderByCreateAtAsc(Facility facility);
    Optional<FacilityComment> findByIdAndStatus(Long id, FacilityComment.Status status);
}
