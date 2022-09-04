package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import com.heyhong.HeyHong.facility.repository.FacilityRepository;
import com.heyhong.HeyHong.users.entity.LikeFacility;
import com.heyhong.HeyHong.users.entity.LikeFacilityCategory;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.LikeFacilityCategoryRepository;
import com.heyhong.HeyHong.users.repository.LikeFacilityRepository;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UsersRepository usersRepository;
    private final LikeFacilityCategoryRepository likeFacilityCategoryRepository;
    private final FacilityCategoryRepository facilityCategoryRepository;
    private final FacilityRepository facilityRepository;
    private final LikeFacilityRepository likeFacilityRepository;

    /**
     * 시설 카테고리 즐겨찾기
     * @param user
     * @param facilityCategoryPk
     */
    @Transactional(rollbackFor = Exception.class)
    public void likeFacilityCategory(Users user, Long facilityCategoryPk) throws Exception{

        FacilityCategory facilityCategory = facilityCategoryRepository.findById(facilityCategoryPk).orElseThrow(()->new NoSuchElementException("해당 시설 카테고리가 존재하지 않습니다."));

        Optional<LikeFacilityCategory> likeFacilityCategory = likeFacilityCategoryRepository.findByUserAndFacilityCategory(user, facilityCategory);

        if(!likeFacilityCategory.isPresent()){
            // 처음으로 즐겨찾기 되는 경우
            LikeFacilityCategory nlikeFacilityCategory = new LikeFacilityCategory(user, facilityCategory);
            likeFacilityCategoryRepository.save(nlikeFacilityCategory);
        }else if(likeFacilityCategory.get().getStatus().equals(LikeFacilityCategory.Status.ACTIVE)){
            throw new NoSuchElementException("이미 즐겨찾기 완료한 시설 카테고리입니다.");
        }else{
            likeFacilityCategory.get().setStatus(LikeFacilityCategory.Status.ACTIVE);
            likeFacilityCategoryRepository.save(likeFacilityCategory.get());
        }


    }

    /**
     * 시설 카테고리 즐겨찾기 취소
     * @param user
     * @param facilityCategoryPk
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlikeFacilityCategory(Users user, Long facilityCategoryPk) throws Exception{

        FacilityCategory facilityCategory = facilityCategoryRepository.findById(facilityCategoryPk).orElseThrow(()-> new NoSuchElementException("해당 시설 카테고리가 존재하지 않습니다. facilityCategoryPk를 확인해주세요."));

        LikeFacilityCategory likeFacilityCategory = likeFacilityCategoryRepository.findByUserAndFacilityCategory(user, facilityCategory).orElseThrow(()-> new NoSuchElementException("즐겨찾기 한 내역이 존재하지 않습니다."));

        if(likeFacilityCategory.getStatus().equals(LikeFacilityCategory.Status.INACTIVE)){
            throw new NoSuchElementException("이미 즐겨찾기 취소 상태입니다.");
        }

        likeFacilityCategory.setStatus(LikeFacilityCategory.Status.INACTIVE);
        likeFacilityCategoryRepository.save(likeFacilityCategory);
    }

    /**
     * 시설 즐겨찾기 여부 체크
     * @param user
     * @param facility
     * @return
     */
    public boolean checkFacilityLike(Users user, Facility facility){
        return likeFacilityRepository.existsByUserAndFacilityAndStatus(user, facility, LikeFacility.Status.ACTIVE);
    }

    /**
     * 시설 즐겨찾기
     * @param user
     * @param facilityPk
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void likeFacility(Users user, Long facilityPk) throws Exception{

        Facility facility = facilityRepository.findByIdAndStatus(facilityPk, Facility.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 시설이 존재하지 않습니다."));

        Optional<LikeFacility> likeFacility = likeFacilityRepository.findByUserAndFacility(user, facility);

        if(!likeFacility.isPresent()){
            // 처음으로 즐겨찾기 되는 경우
            LikeFacility nlikeFacility = new LikeFacility(user, facility);
            likeFacilityRepository.save(nlikeFacility);
        }else if(likeFacility.get().getStatus().equals(LikeFacility.Status.ACTIVE)){
            throw new NoSuchElementException("이미 즐겨찾기 완료한 시설입니다.");
        }else{
            likeFacility.get().setStatus(LikeFacility.Status.ACTIVE);
            likeFacilityRepository.save(likeFacility.get());
        }



    }

    /**
     * 시설 즐겨찾기 취소
     * @param user
     * @param facilityPk
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlikeFacility(Users user, Long facilityPk){
        Facility facility = facilityRepository.findByIdAndStatus(facilityPk, Facility.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 시설이 존재하지 않습니다."));

        LikeFacility likeFacility = likeFacilityRepository.findByUserAndFacility(user, facility).orElseThrow(()-> new NoSuchElementException("즐겨찾기한 내역이 존재하지 않습니다."));

        if(likeFacility.getStatus().equals(LikeFacility.Status.INACTIVE)){
            throw new NoSuchElementException("이미 즐겨찾기 취소 상태입니다.");
        }

        likeFacility.setStatus(LikeFacility.Status.INACTIVE);
        likeFacilityRepository.save(likeFacility);
    }
}
