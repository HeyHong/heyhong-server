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
     * @param userPk
     * @param facilityCategoryPk
     */
    @Transactional(rollbackFor = Exception.class)
    public void likeFacilityCategory(Long userPk, Long facilityCategoryPk) throws Exception{

        Users user = usersRepository.findById(userPk).orElseThrow();

        FacilityCategory facilityCategory = facilityCategoryRepository.findById(facilityCategoryPk).orElseThrow();

        if(likeFacilityCategoryRepository.existsByUserAndFacilityCategory(user, facilityCategory) == true){
            throw new Exception("이미 즐겨찾기 완료한 시설 카테고리입니다.");
        }
        LikeFacilityCategory likeFacilityCategory = new LikeFacilityCategory(user, facilityCategory);
        likeFacilityCategoryRepository.save(likeFacilityCategory);
    }

    /**
     * 시설 카테고리 즐겨찾기 취소
     * @param userPk
     * @param facilityCategoryPk
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlikeFacilityCategory(Long userPk, Long facilityCategoryPk) throws Exception{
        Users user = usersRepository.findById(userPk).orElseThrow(() -> new NoSuchElementException("해당 User가 존재하지 않습니다. userPk를 확인해주세요."));

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
     * @param userPk
     * @param facilityPk
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void likeFacility(Long userPk, Long facilityPk) throws Exception{
        Users user = usersRepository.findById(userPk).orElseThrow(() -> new NoSuchElementException("해당 User가 존재하지 않습니다. userPk를 확인해주세요."));

        Facility facility = facilityRepository.findByIdAndStatus(facilityPk, Facility.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 시설이 존재하지 않습니다."));

        if(checkFacilityLike(user, facility)){
            throw new Exception("이미 즐겨찾기 완료한 시설 입니다.");
        }

        likeFacilityRepository.save(new LikeFacility(user, facility));
        return;

    }

    /**
     * 시설 즐겨찾기 취소
     * @param userPk
     * @param facilityPk
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlikeFacility(Long userPk, Long facilityPk){
        Users user = usersRepository.findById(userPk).orElseThrow(() -> new NoSuchElementException("해당 User가 존재하지 않습니다. userPk를 확인해주세요."));

        Facility facility = facilityRepository.findByIdAndStatus(facilityPk, Facility.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 시설이 존재하지 않습니다."));

        LikeFacility likeFacility = likeFacilityRepository.findByUserAndFacility(user, facility).orElseThrow(()-> new NoSuchElementException("즐겨찾기한 내역이 존재하지 않습니다."));

        if(likeFacility.getStatus().equals(LikeFacility.Status.INACTIVE)){
            throw new NoSuchElementException("이미 즐겨찾기 취소 상태입니다.");
        }
    }
}
