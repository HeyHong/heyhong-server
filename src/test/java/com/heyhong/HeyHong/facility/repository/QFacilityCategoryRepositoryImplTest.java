package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QFacilityCategoryRepositoryImplTest {

    private final FacilityCategoryRepository qFacilityCategoryRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public QFacilityCategoryRepositoryImplTest(FacilityCategoryRepository qFacilityCategoryRepository, UsersRepository usersRepository){
        this.qFacilityCategoryRepository = qFacilityCategoryRepository;
        this.usersRepository = usersRepository;
    }

//    @Test
//    public void facilityCateogoryListTest() throws Exception{
//        //given
//        Users user = usersRepository.findByUserId("kiyoon3609").get();
//        //when
//        List<FacilityCategoryListDto> facilityCategoryList = qFacilityCategoryRepository.findFacilityCategoryListAll(user);
//        //then
//        for(FacilityCategoryListDto fc : facilityCategoryList){
//            System.out.println("카테고리 pk : " + fc.getFacilityCategoryPk() + "/ status  :  " + fc.getLikeStatus());
//
//        }
//    }

}