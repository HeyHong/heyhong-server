package com.heyhong.HeyHong.facility.service;

import com.heyhong.HeyHong.facility.dto.FacilityCategoryDto;
import com.heyhong.HeyHong.facility.dto.FaciltyCategoryGroupDto;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityCategoryRepository facilityCategoryRepository;

    public List<FaciltyCategoryGroupDto> getFacilityCategory() throws Exception{

        List<FacilityCategory> fcs = facilityCategoryRepository.findAll();

        // 편의시설, 행정시설, 학생시설
        FaciltyCategoryGroupDto fcg1 = new FaciltyCategoryGroupDto("편의시설");
        FaciltyCategoryGroupDto fcg2 = new FaciltyCategoryGroupDto("헹정시설");
        FaciltyCategoryGroupDto fcg3 = new FaciltyCategoryGroupDto("학생시설");


        for(FacilityCategory f : fcs){

            FacilityCategoryDto fc = new FacilityCategoryDto(f.getId(), f.getName(), f.getImageUrl());

            if(f.getCategoryGroup().equals("편의시설")){
                fcg1.addFacilityCategory(fc);
            }
            else if(f.getCategoryGroup().equals("행정시설")){
                fcg2.addFacilityCategory(fc);
            }
            else if(f.getCategoryGroup().equals("학생시설")){
                fcg3.addFacilityCategory(fc);
            }
            else{
                throw new Exception("해당 FacilityCategoryGroupDto 존재하지 않음");
            }
        }


        List<FaciltyCategoryGroupDto> result = new ArrayList<>();
        result.add(fcg1);
        result.add(fcg2);
        result.add(fcg3);

        return result;

    }
}
