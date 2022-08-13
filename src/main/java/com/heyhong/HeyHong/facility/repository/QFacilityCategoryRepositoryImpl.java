package com.heyhong.HeyHong.facility.repository;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.QFacility;
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
}
