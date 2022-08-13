package com.heyhong.HeyHong.facility.service;

import com.heyhong.HeyHong.facility.dto.FacilityCategoryDto;
import com.heyhong.HeyHong.facility.dto.FacilityCommentCountDto;
import com.heyhong.HeyHong.facility.dto.FacilityListItemDto;
import com.heyhong.HeyHong.facility.dto.FaciltyCategoryGroupDto;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import com.heyhong.HeyHong.facility.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityCategoryRepository facilityCategoryRepository;
    private final FacilityRepository facilityRepository;

    /**
     * facility 카테고리 가져오기
     * @return
     * @throws Exception
     */
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


    /**
     * facility List 가져오기
     * @param facilityCategoryId
     * @return
     */
    public List<FacilityListItemDto> getFacilityList(Long facilityCategoryId){

        List<Facility> facilities = facilityRepository.findAllByFacilityCategoryOrderById(facilityCategoryId);

        if(facilities.isEmpty()){
            throw new NoSuchElementException("해당 시설 카테고리는 아직 등록되지 않았거나 존재하지 않습니다.");
        }

        ArrayList<Long> facilityIds = new ArrayList<>();
        for(Facility f : facilities){
            facilityIds.add(f.getId());
        }
        List<FacilityCommentCountDto> commentCounts = facilityRepository.findFacilityCommentCount(facilityIds);

        // facilty 정보와 comment 갯수 DTO에 맵핑
        List<FacilityListItemDto> result = new ArrayList<>();

        if(commentCounts.isEmpty()){
            System.out.println("-----comment 없음======");
            for(Facility f : facilities){
                result.add(new FacilityListItemDto(f));
            }
        }else{
            System.out.println("------있음======");
            int ci = 0;
            int maxCi = commentCounts.size();
            for(Facility f : facilities){

                if(ci<maxCi && commentCounts.get(ci).getFacilityId() == f.getId()){
                    result.add(new FacilityListItemDto(f, commentCounts.get(ci).getCommentCount()));
                    ci++;
                }else{
                    result.add(new FacilityListItemDto(f));
                }
            }
        }


        return result;
    }


//    public void queryTest(){
//        ArrayList<Long> l = new ArrayList<Long>();
//        l.add(2L);
//        l.add(3L);
//        List<FacilityCommentCountDto> facilities = facilityRepository.findFacilityCommentCount(l);
//        for(FacilityCommentCountDto f : facilities){
//            System.out.println("-----------");
//            System.out.println(f.getFacilityId());
//            System.out.println(f.getCommentCount());
//        }
//    }
}
