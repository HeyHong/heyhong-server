package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import com.heyhong.HeyHong.users.entity.LikeFacilityCategory;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.LikeFacilityCategoryRepository;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UsersRepository usersRepository;
    private final LikeFacilityCategoryRepository likeFacilityCategoryRepository;
    private final FacilityCategoryRepository facilityCategoryRepository;

    /**
     * 시설 카테고리 좋아요 service
     * @param userPk
     * @param facilityCategoryPk
     */
    @Transactional
    public void likeFacilityCategory(Long userPk, Long facilityCategoryPk) throws Exception{

        Users user = usersRepository.findById(userPk).orElseThrow();
        System.out.println(" 찯ㅈ음");
        FacilityCategory facilityCategory = facilityCategoryRepository.findById(facilityCategoryPk).orElseThrow();

        if(likeFacilityCategoryRepository.existsByUserAndFacilityCategory(user, facilityCategory) == true){
            System.out.println("머지");
            throw new Exception("이미 좋아요한 시설 카테고리입니다.");
        }
        LikeFacilityCategory likeFacilityCategory = new LikeFacilityCategory(user, facilityCategory);
        likeFacilityCategoryRepository.save(likeFacilityCategory);
    }
}
