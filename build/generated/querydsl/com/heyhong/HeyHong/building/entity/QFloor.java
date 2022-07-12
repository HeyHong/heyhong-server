package com.heyhong.HeyHong.building.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFloor is a Querydsl query type for Floor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFloor extends EntityPathBase<Floor> {

    private static final long serialVersionUID = 1088434634L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFloor floor = new QFloor("floor");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final QBuilding building;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final ListPath<com.heyhong.HeyHong.facility.entity.Facility, com.heyhong.HeyHong.facility.entity.QFacility> facilities = this.<com.heyhong.HeyHong.facility.entity.Facility, com.heyhong.HeyHong.facility.entity.QFacility>createList("facilities", com.heyhong.HeyHong.facility.entity.Facility.class, com.heyhong.HeyHong.facility.entity.QFacility.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public final ListPath<Room, QRoom> rooms = this.<Room, QRoom>createList("rooms", Room.class, QRoom.class, PathInits.DIRECT2);

    public final EnumPath<com.heyhong.HeyHong.users.entity.Users.Status> status = createEnum("status", com.heyhong.HeyHong.users.entity.Users.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QFloor(String variable) {
        this(Floor.class, forVariable(variable), INITS);
    }

    public QFloor(Path<? extends Floor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFloor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFloor(PathMetadata metadata, PathInits inits) {
        this(Floor.class, metadata, inits);
    }

    public QFloor(Class<? extends Floor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.building = inits.isInitialized("building") ? new QBuilding(forProperty("building")) : null;
    }

}

