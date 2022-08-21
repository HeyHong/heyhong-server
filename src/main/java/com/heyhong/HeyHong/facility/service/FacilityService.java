package com.heyhong.HeyHong.facility.service;

import com.heyhong.HeyHong.facility.dto.*;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.entity.FacilityComment;
import com.heyhong.HeyHong.facility.entity.FacilityImage;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import com.heyhong.HeyHong.facility.repository.FacilityCommentRepository;
import com.heyhong.HeyHong.facility.repository.FacilityImageRepository;
import com.heyhong.HeyHong.facility.repository.FacilityRepository;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityCategoryRepository facilityCategoryRepository;
    private final FacilityRepository facilityRepository;
    private final FacilityCommentRepository facilityCommentRepository;
    private final FacilityImageRepository facilityImageRepository;
    private final LikeService likeService;

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
            // comment가 없는 경우
            for(Facility f : facilities){
                result.add(new FacilityListItemDto(f));
            }
        }else{
            // comment가 있는 경우
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


    /**
     * 시설 댓글/대댓글 달기
     * @param facilityPk
     * @param contents
     * @param user
     */
    public void createFacilityComment(Long facilityPk, String contents, Users user, Long replyCommentPk){

        Facility facility = facilityRepository.findById(facilityPk).orElseThrow(()->new NoSuchElementException("해당 시설이 존재하지 않습니다"));
        FacilityComment facilityComment = null;
        if(replyCommentPk != null){
            // 댓글의 종류가 대댓글인 경우
            FacilityComment replyFacilityComment =  facilityCommentRepository.findById(replyCommentPk)
                    .orElseThrow(()-> new NoSuchElementException("해당 대댓글 대상 댓글이 존재하지 않습니다. replyCommentPk를 확인해주세요."));
            facilityComment = new FacilityComment(facility, user, contents, replyFacilityComment);
        }else{
            // 댓글의 종류가 최초 댓글인 경우
            facilityComment = new FacilityComment(facility, user, contents);
        }

        facilityCommentRepository.save(facilityComment);

    }


    /**
     * facility 상세 정보 조회
     * @param facilityPk
     * @return
     */
    public FacilityDetailRes readFacilityDetail(Long facilityPk, Users user){

        Facility facility = facilityRepository.findByIdAndStatus(facilityPk, Facility.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 시설이 존재하지 않습니다. pk를 다시 한번 확인해주세요"));

        FacilityDetailRes result;
        //facility 기반으로 FacilityDetailRes 생성
        if(facility.getFloor() == null) {
            System.out.println("없음");
            result = new FacilityDetailRes(facility);
        }else{
            System.out.println("있음");
            Long floorPk = facility.getFloor().getId();
            result = new FacilityDetailRes(facility, floorPk);
        }


        //facility entity에 해당하는 이미지 가져오기 및 FacilityDetailRes에 set
        result.setImages(getFacilityImages(facility));

        //시설 찜하기 여부
        result.setLikeStatus(likeService.checkFacilityLike(user, facility));

        //하위 시설을 가진 경우 ) 카테고리가 행정 기관 (facility_id = 12)
//        if(facility.getFacilityCategory().getId() == 12){
//            //하위 부속 기관 list
//            List<Facility> subFacilities = facilityRepository.findByParent(facility);
//            //반환할 FacilityDetailRes DTO
//            List<FacilityDetailRes> subFacilityDetailRes = new ArrayList<>();
//            for(Facility sf : subFacilities){
//                //부속기관 entitiy를 활용하여 FacilityDetailRes 생성
//                FacilityDetailRes facilityDetailRes = new FacilityDetailRes(sf);
//                //부속기관 entity에 해당하는 이미지 가져오기 및 FacilityDetailRes에 set
//                facilityDetailRes.setImages(this.getFacilityImages(sf));
//                subFacilityDetailRes.add(facilityDetailRes);
//            }
//
//            result.setSubFacilities(subFacilityDetailRes);
//        }

        return result;


    }


    /**
     * 시설 이미지 가져오기 - private
     * @param facility
     * @return
     */
    private List<String> getFacilityImages(Facility facility){
        List<FacilityImage> facilityImages = facilityImageRepository.findByFacilityAndStatus(facility, FacilityImage.Status.ACTIVE);
        List<String> result = new ArrayList<>();
        for(FacilityImage fm : facilityImages){
            result.add(fm.getImageUrl());
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
