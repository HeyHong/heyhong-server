package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.dto.FacilityCommentCountDto;
import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.QFacility;
import com.heyhong.HeyHong.facility.entity.QFacilityComment;
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
    public List<Facility> findAllByFacilityCategoryOrderById(Long facilityCategoryId){
        return jpaQueryFactory.selectFrom(qFacility)
                .where(qFacility.facilityCategory.id.eq(facilityCategoryId))
                .orderBy(qFacility.id.asc())
                .fetch();

    }
}
