package com.heyhong.HeyHong.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeFacilityCategory is a Querydsl query type for LikeFacilityCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeFacilityCategory extends EntityPathBase<LikeFacilityCategory> {

    private static final long serialVersionUID = -35575540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeFacilityCategory likeFacilityCategory = new QLikeFacilityCategory("likeFacilityCategory");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final com.heyhong.HeyHong.facility.entity.QFacilityCategory facilityCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<LikeFacilityCategory.Status> status = createEnum("status", LikeFacilityCategory.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final QUsers user;

    public QLikeFacilityCategory(String variable) {
        this(LikeFacilityCategory.class, forVariable(variable), INITS);
    }

    public QLikeFacilityCategory(Path<? extends LikeFacilityCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeFacilityCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeFacilityCategory(PathMetadata metadata, PathInits inits) {
        this(LikeFacilityCategory.class, metadata, inits);
    }

    public QLikeFacilityCategory(Class<? extends LikeFacilityCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilityCategory = inits.isInitialized("facilityCategory") ? new com.heyhong.HeyHong.facility.entity.QFacilityCategory(forProperty("facilityCategory")) : null;
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

