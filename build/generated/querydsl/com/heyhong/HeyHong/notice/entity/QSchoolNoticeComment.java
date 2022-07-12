package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchoolNoticeComment is a Querydsl query type for SchoolNoticeComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchoolNoticeComment extends EntityPathBase<SchoolNoticeComment> {

    private static final long serialVersionUID = -1584817235L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchoolNoticeComment schoolNoticeComment = new QSchoolNoticeComment("schoolNoticeComment");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final ListPath<SchoolNoticeComment, QSchoolNoticeComment> children = this.<SchoolNoticeComment, QSchoolNoticeComment>createList("children", SchoolNoticeComment.class, QSchoolNoticeComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSchoolNoticeComment parent;

    public final QSchoolNotice schoolNotice;

    public final EnumPath<SchoolNoticeComment.Status> status = createEnum("status", SchoolNoticeComment.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final com.heyhong.HeyHong.users.entity.QUsers user;

    public QSchoolNoticeComment(String variable) {
        this(SchoolNoticeComment.class, forVariable(variable), INITS);
    }

    public QSchoolNoticeComment(Path<? extends SchoolNoticeComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchoolNoticeComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchoolNoticeComment(PathMetadata metadata, PathInits inits) {
        this(SchoolNoticeComment.class, metadata, inits);
    }

    public QSchoolNoticeComment(Class<? extends SchoolNoticeComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QSchoolNoticeComment(forProperty("parent"), inits.get("parent")) : null;
        this.schoolNotice = inits.isInitialized("schoolNotice") ? new QSchoolNotice(forProperty("schoolNotice"), inits.get("schoolNotice")) : null;
        this.user = inits.isInitialized("user") ? new com.heyhong.HeyHong.users.entity.QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

