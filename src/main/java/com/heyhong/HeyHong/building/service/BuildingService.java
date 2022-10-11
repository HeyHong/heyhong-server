package com.heyhong.HeyHong.building.service;

import com.heyhong.HeyHong.building.dto.RetrieveFloorRes;
import com.heyhong.HeyHong.building.entity.Floor;
import com.heyhong.HeyHong.building.repository.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final FloorRepository floorRepository;

    /**
     * 건물 층 도면 조회
     * @param floorId
     * @return
     */
    public RetrieveFloorRes retrieveFloorMapImageUrl(Long floorId){
        Floor floor = floorRepository.findById(floorId).orElseThrow(() -> new NoSuchElementException("해당하는 floor가 존재하지 않습니다."));

        return new RetrieveFloorRes(floor.getNumber(), floor.getMapImageUrl());
    }
}
