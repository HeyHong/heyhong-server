package com.heyhong.HeyHong.building.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = 1143849821L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoom room = new QRoom("room");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final QFloor floor;

    public final StringPath homepage_name = createString("homepage_name");

    public final StringPath homepage_url = createString("homepage_url");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath number = createString("number");

    public final StringPath opening_hours = createString("opening_hours");

    public final EnumPath<Room.Status> status = createEnum("status", Room.Status.class);

    public final StringPath telephone1 = createString("telephone1");

    public final StringPath telephone1_name = createString("telephone1_name");

    public final StringPath telephone2 = createString("telephone2");

    public final StringPath telephone2_name = createString("telephone2_name");

    public final StringPath telephone3 = createString("telephone3");

    public final StringPath telephone3_name = createString("telephone3_name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QRoom(String variable) {
        this(Room.class, forVariable(variable), INITS);
    }

    public QRoom(Path<? extends Room> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoom(PathMetadata metadata, PathInits inits) {
        this(Room.class, metadata, inits);
    }

    public QRoom(Class<? extends Room> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.floor = inits.isInitialized("floor") ? new QFloor(forProperty("floor"), inits.get("floor")) : null;
    }

}

