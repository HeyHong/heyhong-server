package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.dto.FacilityCommentCountDto;
import com.heyhong.HeyHong.facility.dto.LikedFacilityDao;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.QFacility;
import com.heyhong.HeyHong.facility.entity.QFacilityComment;
import com.heyhong.HeyHong.users.entity.LikeFacility;
import com.heyhong.HeyHong.users.entity.QLikeFacility;
import com.heyhong.HeyHong.users.entity.Users;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QFacilityRepositoryImpl implements QFacilityRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private QFacilityComment qFacilityComment = QFacilityComment.facilityComment;
    private QFacility qFacility = QFacility.facility;
    private QLikeFacility qLikeFacility = QLikeFacility.likeFacility;

    @Override
    public List<FacilityCommentCountDto> findFacilityCommentCount(ArrayList<Long> ids){
        return jpaQueryFactory.select(
                Projections.bean(FacilityCommentCountDto.class,
                        qFacilityComment.facility.id.as("facilityId"),
                        qFacilityComment.count().as("commentCount")
                        ))
                .from(qFacilityComment)
                .groupBy(qFacilityComment.facility)
                .having(qFacilityComment.facility.id.in(ids))
                .orderBy(qFacilityComment.facility.id.asc())
                .fetch();
    }

    @Override
    public List<Facility> findAllByFacilityCategoryAndStatusOrderById(Long facilityCategoryId, Facility.Status status){
        return jpaQueryFactory.selectFrom(qFacility)
                .where(qFacility.facilityCategory.id.eq(facilityCategoryId))
                .where(qFacility.status.eq(status))
                .orderBy(qFacility.id.asc())
                .fetch();

    }

    @Override
    public List<LikedFacilityDao> findAllLikedFacility(Users user) {

        return jpaQueryFactory.select(
                Projections.bean(LikedFacilityDao.class,
                        qFacility.id.as("facilityPk"),
                        qFacility.name.as("name")))
                .from(qFacility)
                .innerJoin(qFacility.likeFacilities, qLikeFacility)
                .on(qLikeFacility.user.eq(user))
                .on(qLikeFacility.status.eq(LikeFacility.Status.ACTIVE))
                .fetch();
    }
}
