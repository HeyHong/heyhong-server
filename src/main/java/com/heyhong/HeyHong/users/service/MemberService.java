package com.heyhong.HeyHong.users.service;


import com.heyhong.HeyHong.config.exception.UpdateSameElementException;
import com.heyhong.HeyHong.facility.dto.LikedFacilityCategoryDao;
import com.heyhong.HeyHong.facility.dto.LikedFacilityDao;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityComment;
import com.heyhong.HeyHong.facility.repository.FacilityCategoryRepository;
import com.heyhong.HeyHong.facility.repository.FacilityCommentRepository;
import com.heyhong.HeyHong.facility.repository.FacilityRepository;
import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.hongik.repository.CollegeRepository;
import com.heyhong.HeyHong.hongik.repository.DepartmentRepository;
import com.heyhong.HeyHong.s3.S3Uploader;
import com.heyhong.HeyHong.users.dto.LikedItem;
import com.heyhong.HeyHong.users.dto.MainRes;
import com.heyhong.HeyHong.users.dto.ProfileDto;
import com.heyhong.HeyHong.users.dto.RecentComment;
import com.heyhong.HeyHong.users.entity.LikeFacility;
import com.heyhong.HeyHong.users.entity.LikeFacilityCategory;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.LikeFacilityCategoryRepository;
import com.heyhong.HeyHong.users.repository.LikeFacilityRepository;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import com.heyhong.HeyHong.util.MultipartImage;
import com.heyhong.HeyHong.util.ResizingUtil;
import lombok.RequiredArgsConstructor;
import marvin.image.MarvinImage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final UsersRepository usersRepository;
    private final FacilityRepository facilityRepository;
    private final FacilityCategoryRepository facilityCategoryRepository;
    private final FacilityCommentRepository facilityCommentRepository;
    private final LikeFacilityRepository likeFacilityRepository;
    private final LikeFacilityCategoryRepository likeFacilityCategoryRepository;

    private final S3Uploader s3Uploader;
    private final ResizingUtil resizingUtil;


    /**
     * 로그아웃
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void logout(Users user) throws Exception{
        if(user.getJwtToken().equals(null)){
            throw new Exception("이미 로그아웃된 사용자입니다.");
        }else{
            user.setJwtToken(null);
            usersRepository.save(user);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void sucession(Users user) throws Exception{
        // 댓글 모두 불러오기
        List<FacilityComment> facilityComments = facilityCommentRepository.findAllByUser(user);
        List<LikeFacility> likeFacilities = likeFacilityRepository.findAllByUser(user);
        List<LikeFacilityCategory> likeFacilityCategories = likeFacilityCategoryRepository.findAllByUser(user);

        // (개선) 성능 고려하여 배치 update JPQL 사용하기
        for(FacilityComment facilityComment : facilityComments){
            facilityComment.setUserNull();
        }

        for(LikeFacility likeFacility : likeFacilities){
            likeFacility.setUserNullAndInactive();
        }

        for(LikeFacilityCategory likeFacilityCategory : likeFacilityCategories){
            likeFacilityCategory.setUserNullAndInactive();
        }

        usersRepository.delete(user);

    }

    /**
     * 메인 화면
     * @param user
     * @return
     */
    public MainRes getMain(Users user){

        List<LikedItem> likedItemResult = new ArrayList<>();
//        List<RecentComment> recentCommentResult = new ArrayList<>();

        // 즐겨찾기 리스트 repository로부터 추출
        List<LikedFacilityCategoryDao> allLikedFacilityCategory = facilityCategoryRepository.findAllLikedFacilityCategory(user);
        List<LikedFacilityDao> allLikedFacility = facilityRepository.findAllLikedFacility(user);

        //즐겨찾기 리스트 dto 정제
        for(LikedFacilityCategoryDao fcd : allLikedFacilityCategory){
            likedItemResult.add(new LikedItem(LikedItem.LikeType.facilityCategory, fcd.getFacilityCategoryPk(), fcd.getName()));
        }

        for(LikedFacilityDao fd : allLikedFacility){
            likedItemResult.add(new LikedItem(LikedItem.LikeType.facility, fd.getFacilityPk(), fd.getName()));
        }

        //최근 댓글 3개 repository로부터 추출
        List<RecentComment> recentCommentResult = facilityCommentRepository.findRecentCommentByLimit(3L);

        return new MainRes("준비중", likedItemResult, recentCommentResult);

    }

    /**
     * 프로필 - 유져 정보 조회
     * @param userPk
     * @return
     */
    public ProfileDto retrieveUserProfileInfo(Long userPk){

        Users user = usersRepository.getById(userPk);

        return new ProfileDto(user.getNickname(), user.getProfileImageUrl(), user.getCollege().getName(), user.getDepartment().getName(), user.getEmail(), user.getUserId());
    }


    /**
     *
     * @param file
     * @param user
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserProfileImage(MultipartFile file, Users user) throws Exception {

        if(!Objects.requireNonNull(file.getContentType()).contains("image")){
            throw new IllegalArgumentException("image 형식의 파일이 아닙니다.");
        }

        MultipartFile resizedImage = resizingUtil.resizeProfileImage(file, user.getUserId(), 70, 70);

        String imageUrl = s3Uploader.upload(resizedImage, "user_image");

        user.setProfileImage(imageUrl);
        usersRepository.save(user);

    }

    /**
     * 프로필 - 학부/학과 변경
     * @param user
     * @param curUserCollegePk
     * @param curUserDepartmentPk
     * @param collegePk
     * @param departmentPk
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateMemberDepartment(Users user, Long curUserCollegePk, Long curUserDepartmentPk, Long collegePk, Long departmentPk) throws Exception{

        College college = collegeRepository.findById(collegePk).orElseThrow(()-> new NoSuchElementException("해당 대학(학부)이 존재하지 않습니다."));

        Department department = departmentRepository.findById(departmentPk).orElseThrow(()-> new NoSuchElementException("해당 학과가 존재하지 않습니다."));


        if(curUserCollegePk == college.getId() && curUserDepartmentPk == department.getId()){
            throw new UpdateSameElementException("기존에 등록된 학부, 학과와 같습니다.");
        }else if(curUserCollegePk == college.getId()){
            throw new UpdateSameElementException("기존에 등록된 학부와 같습니다.");
        }else if(curUserDepartmentPk == department.getId()){
            throw new UpdateSameElementException("기존에 등록된 학과와 같습니다.");
        }

        user.setCollegeAndDepartment(college, department);
        usersRepository.save(user);
    }


}
