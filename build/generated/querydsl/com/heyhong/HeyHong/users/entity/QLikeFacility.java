package com.heyhong.HeyHong.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeFacility is a Querydsl query type for LikeFacility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeFacility extends EntityPathBase<LikeFacility> {

    private static final long serialVersionUID = 1208604142L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeFacility likeFacility = new QLikeFacility("likeFacility");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final com.heyhong.HeyHong.facility.entity.QFacilityCategory facilityCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<LikeFacility.Status> status = createEnum("status", LikeFacility.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final QUsers user;

    public QLikeFacility(String variable) {
        this(LikeFacility.class, forVariable(variable), INITS);
    }

    public QLikeFacility(Path<? extends LikeFacility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeFacility(PathMetadata metadata, PathInits inits) {
        this(LikeFacility.class, metadata, inits);
    }

    public QLikeFacility(Class<? extends LikeFacility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilityCategory = inits.isInitialized("facilityCategory") ? new com.heyhong.HeyHong.facility.entity.QFacilityCategory(forProperty("facilityCategory")) : null;
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

