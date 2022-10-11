package com.heyhong.HeyHong.building.repository;

import com.heyhong.HeyHong.building.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    Optional<Floor> findById(Long id);
}
