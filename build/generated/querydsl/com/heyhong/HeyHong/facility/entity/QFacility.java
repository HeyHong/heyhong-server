package com.heyhong.HeyHong.facility.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacility is a Querydsl query type for Facility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacility extends EntityPathBase<Facility> {

    private static final long serialVersionUID = -1431159276L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacility facility = new QFacility("facility");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final ListPath<Facility, QFacility> facilities = this.<Facility, QFacility>createList("facilities", Facility.class, QFacility.class, PathInits.DIRECT2);

    public final QFacilityCategory facilityCategory;

    public final com.heyhong.HeyHong.building.entity.QFloor floor;

    public final StringPath homepage1_name = createString("homepage1_name");

    public final StringPath homepage1_url = createString("homepage1_url");

    public final StringPath homepage2_name = createString("homepage2_name");

    public final StringPath homepage2_url = createString("homepage2_url");

    public final StringPath homepage3_name = createString("homepage3_name");

    public final StringPath homepage3_url = createString("homepage3_url");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image_url = createString("image_url");

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final StringPath opening_hours = createString("opening_hours");

    public final QFacility parent;

    public final EnumPath<Facility.Status> status = createEnum("status", Facility.Status.class);

    public final StringPath telephone1 = createString("telephone1");

    public final StringPath telephone1_name = createString("telephone1_name");

    public final StringPath telephone2 = createString("telephone2");

    public final StringPath telephone2_name = createString("telephone2_name");

    public final StringPath telephone3 = createString("telephone3");

    public final StringPath telephone3_name = createString("telephone3_name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QFacility(String variable) {
        this(Facility.class, forVariable(variable), INITS);
    }

    public QFacility(Path<? extends Facility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacility(PathMetadata metadata, PathInits inits) {
        this(Facility.class, metadata, inits);
    }

    public QFacility(Class<? extends Facility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilityCategory = inits.isInitialized("facilityCategory") ? new QFacilityCategory(forProperty("facilityCategory")) : null;
        this.floor = inits.isInitialized("floor") ? new com.heyhong.HeyHong.building.entity.QFloor(forProperty("floor"), inits.get("floor")) : null;
        this.parent = inits.isInitialized("parent") ? new QFacility(forProperty("parent"), inits.get("parent")) : null;
    }

}

