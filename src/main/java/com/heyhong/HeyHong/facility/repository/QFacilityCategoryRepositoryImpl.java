package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.dto.FacilityCategoryDao;
import com.heyhong.HeyHong.facility.dto.LikedFacilityCategoryDao;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.QFacility;
import com.heyhong.HeyHong.facility.entity.QFacilityCategory;
import com.heyhong.HeyHong.users.entity.LikeFacilityCategory;
import com.heyhong.HeyHong.users.entity.QLikeFacilityCategory;
import com.heyhong.HeyHong.users.entity.Users;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QFacilityCategoryRepositoryImpl implements QFacilityCategoryRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Facility> findFacilityAll(){
        QFacility qFacility = QFacility.facility;
        return jpaQueryFactory.select(qFacility).from(qFacility).fetch();
    }

    @Override
    public List<FacilityCategoryDao> findFacilityCategoryListAll(Users user) {
        QFacilityCategory qFacilityCategory = QFacilityCategory.facilityCategory;
        QLikeFacilityCategory qLikeFacilityCategory = QLikeFacilityCategory.likeFacilityCategory;

        return jpaQueryFactory.select(
                Projections.bean(FacilityCategoryDao.class,
                        qFacilityCategory.id.as("facilityCategoryPk"),
                        qFacilityCategory.categoryGroup.as("categoryGroup"),
                        qFacilityCategory.name.as("name"),
                        qFacilityCategory.imageUrl.as("imageUrl"),
                        qLikeFacilityCategory.status.as("likeStatus")))
                .from(qFacilityCategory)
                .leftJoin(qFacilityCategory.likeFacilityCategories, qLikeFacilityCategory)
                .on(qLikeFacilityCategory.user.eq(user))
                .on(qLikeFacilityCategory.status.eq(LikeFacilityCategory.Status.ACTIVE))
                .fetch();
    }

    @Override
    public List<LikedFacilityCategoryDao> findAllLikedFacilityCategory(Users user) {
        QFacilityCategory qFacilityCategory = QFacilityCategory.facilityCategory;
        QLikeFacilityCategory qLikeFacilityCategory = QLikeFacilityCategory.likeFacilityCategory;

        return jpaQueryFactory.select(
                Projections.bean(LikedFacilityCategoryDao.class,
                        qFacilityCategory.id.as("facilityCategoryPk"),
                        qFacilityCategory.name.as("name")
                        ))
                .from(qFacilityCategory)
                .innerJoin(qFacilityCategory.likeFacilityCategories, qLikeFacilityCategory)
                .on(qLikeFacilityCategory.user.eq(user))
                .on(qLikeFacilityCategory.status.eq(LikeFacilityCategory.Status.ACTIVE))
                .fetch();
    }
}
