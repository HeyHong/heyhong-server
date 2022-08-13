package com.heyhong.HeyHong.facility.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityComment is a Querydsl query type for FacilityComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityComment extends EntityPathBase<FacilityComment> {

    private static final long serialVersionUID = -1956509269L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacilityComment facilityComment = new QFacilityComment("facilityComment");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final EnumPath<FacilityComment.AnonymousStatus> anonymous = createEnum("anonymous", FacilityComment.AnonymousStatus.class);

    public final ListPath<FacilityComment, QFacilityComment> children = this.<FacilityComment, QFacilityComment>createList("children", FacilityComment.class, QFacilityComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final QFacility facility;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QFacilityComment parent;

    public final EnumPath<FacilityComment.Status> status = createEnum("status", FacilityComment.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final com.heyhong.HeyHong.users.entity.QUsers user;

    public QFacilityComment(String variable) {
        this(FacilityComment.class, forVariable(variable), INITS);
    }

    public QFacilityComment(Path<? extends FacilityComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacilityComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacilityComment(PathMetadata metadata, PathInits inits) {
        this(FacilityComment.class, metadata, inits);
    }

    public QFacilityComment(Class<? extends FacilityComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facility = inits.isInitialized("facility") ? new QFacility(forProperty("facility"), inits.get("facility")) : null;
        this.parent = inits.isInitialized("parent") ? new QFacilityComment(forProperty("parent"), inits.get("parent")) : null;
        this.user = inits.isInitialized("user") ? new com.heyhong.HeyHong.users.entity.QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

