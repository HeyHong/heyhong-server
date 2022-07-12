package com.heyhong.HeyHong.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeScrap is a Querydsl query type for NoticeScrap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeScrap extends EntityPathBase<NoticeScrap> {

    private static final long serialVersionUID = -523384123L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeScrap noticeScrap = new QNoticeScrap("noticeScrap");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final com.heyhong.HeyHong.notice.entity.QCouncilNotice councilNotice;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final com.heyhong.HeyHong.notice.entity.QDepartmentNotice departmentNotice;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.heyhong.HeyHong.notice.entity.QSchoolNotice schoolNotice;

    public final EnumPath<NoticeScrap.Status> status = createEnum("status", NoticeScrap.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final QUsers user;

    public QNoticeScrap(String variable) {
        this(NoticeScrap.class, forVariable(variable), INITS);
    }

    public QNoticeScrap(Path<? extends NoticeScrap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeScrap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeScrap(PathMetadata metadata, PathInits inits) {
        this(NoticeScrap.class, metadata, inits);
    }

    public QNoticeScrap(Class<? extends NoticeScrap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.councilNotice = inits.isInitialized("councilNotice") ? new com.heyhong.HeyHong.notice.entity.QCouncilNotice(forProperty("councilNotice"), inits.get("councilNotice")) : null;
        this.departmentNotice = inits.isInitialized("departmentNotice") ? new com.heyhong.HeyHong.notice.entity.QDepartmentNotice(forProperty("departmentNotice"), inits.get("departmentNotice")) : null;
        this.schoolNotice = inits.isInitialized("schoolNotice") ? new com.heyhong.HeyHong.notice.entity.QSchoolNotice(forProperty("schoolNotice"), inits.get("schoolNotice")) : null;
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

