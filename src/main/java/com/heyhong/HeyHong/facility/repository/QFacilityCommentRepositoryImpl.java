package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.FacilityComment;
import com.heyhong.HeyHong.facility.entity.QFacility;
import com.heyhong.HeyHong.facility.entity.QFacilityCategory;
import com.heyhong.HeyHong.facility.entity.QFacilityComment;
import com.heyhong.HeyHong.users.dto.RecentComment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QFacilityCommentRepositoryImpl implements QFacilityCommentRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RecentComment> findRecentCommentByLimit(long limit) {
        QFacilityComment qFacilityComment = QFacilityComment.facilityComment;
        QFacility qFacility = QFacility.facility;
        QFacilityCategory qFacilityCategory = QFacilityCategory.facilityCategory;

        return jpaQueryFactory.select(
                Projections.bean(RecentComment.class,
                        qFacilityCategory.name.as("facilityCategory"),
                        qFacility.name.as("facility"),
                        qFacility.id.as("facilityPk"),
                        qFacilityComment.contents.as("contents")
                        ))
                .from(qFacilityComment)
                .innerJoin(qFacilityComment.facility, qFacility)
                .innerJoin(qFacility.facilityCategory, qFacilityCategory)
                .orderBy(qFacilityComment.createAt.desc())
                .limit(limit)
                .where(qFacilityComment.parent.isNull())
                .fetch();
    }
}
