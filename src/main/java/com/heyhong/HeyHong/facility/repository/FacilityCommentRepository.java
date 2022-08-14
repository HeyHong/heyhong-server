package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.FacilityComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityCommentRepository extends JpaRepository<FacilityComment, Long> {
}
