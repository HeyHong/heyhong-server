package com.heyhong.HeyHong.facility.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityImage is a Querydsl query type for FacilityImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityImage extends EntityPathBase<FacilityImage> {

    private static final long serialVersionUID = 660416423L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacilityImage facilityImage = new QFacilityImage("facilityImage");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final QFacility facility;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final EnumPath<FacilityImage.Status> status = createEnum("status", FacilityImage.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QFacilityImage(String variable) {
        this(FacilityImage.class, forVariable(variable), INITS);
    }

    public QFacilityImage(Path<? extends FacilityImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacilityImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacilityImage(PathMetadata metadata, PathInits inits) {
        this(FacilityImage.class, metadata, inits);
    }

    public QFacilityImage(Class<? extends FacilityImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facility = inits.isInitialized("facility") ? new QFacility(forProperty("facility"), inits.get("facility")) : null;
    }

}

