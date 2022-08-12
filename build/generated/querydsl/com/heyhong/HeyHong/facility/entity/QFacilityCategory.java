package com.heyhong.HeyHong.facility.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityCategory is a Querydsl query type for FacilityCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityCategory extends EntityPathBase<FacilityCategory> {

    private static final long serialVersionUID = 130681650L;

    public static final QFacilityCategory facilityCategory = new QFacilityCategory("facilityCategory");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final StringPath categoryGroup = createString("categoryGroup");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final ListPath<Facility, QFacility> facilities = this.<Facility, QFacility>createList("facilities", Facility.class, QFacility.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final EnumPath<FacilityCategory.Status> status = createEnum("status", FacilityCategory.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QFacilityCategory(String variable) {
        super(FacilityCategory.class, forVariable(variable));
    }

    public QFacilityCategory(Path<? extends FacilityCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFacilityCategory(PathMetadata metadata) {
        super(FacilityCategory.class, metadata);
    }

}

