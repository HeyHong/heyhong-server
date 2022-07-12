package com.heyhong.HeyHong.building.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomComment is a Querydsl query type for RoomComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoomComment extends EntityPathBase<RoomComment> {

    private static final long serialVersionUID = -49500094L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomComment roomComment = new QRoomComment("roomComment");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final EnumPath<RoomComment.AnonymousStatus> anonymous = createEnum("anonymous", RoomComment.AnonymousStatus.class);

    public final ListPath<RoomComment, QRoomComment> children = this.<RoomComment, QRoomComment>createList("children", RoomComment.class, QRoomComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRoomComment parent;

    public final QRoom room;

    public final EnumPath<RoomComment.Status> status = createEnum("status", RoomComment.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final com.heyhong.HeyHong.users.entity.QUsers user;

    public QRoomComment(String variable) {
        this(RoomComment.class, forVariable(variable), INITS);
    }

    public QRoomComment(Path<? extends RoomComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomComment(PathMetadata metadata, PathInits inits) {
        this(RoomComment.class, metadata, inits);
    }

    public QRoomComment(Class<? extends RoomComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QRoomComment(forProperty("parent"), inits.get("parent")) : null;
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new com.heyhong.HeyHong.users.entity.QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

