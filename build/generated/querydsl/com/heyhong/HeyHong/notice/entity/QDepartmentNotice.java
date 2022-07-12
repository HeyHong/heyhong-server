package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDepartmentNotice is a Querydsl query type for DepartmentNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartmentNotice extends EntityPathBase<DepartmentNotice> {

    private static final long serialVersionUID = -36622160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartmentNotice departmentNotice = new QDepartmentNotice("departmentNotice");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final com.heyhong.HeyHong.hongik.entity.QDepartment department;

    public final ListPath<DepartmentNoticeComment, QDepartmentNoticeComment> departmentNoticeComments = this.<DepartmentNoticeComment, QDepartmentNoticeComment>createList("departmentNoticeComments", DepartmentNoticeComment.class, QDepartmentNoticeComment.class, PathInits.DIRECT2);

    public final StringPath file_url1 = createString("file_url1");

    public final StringPath file_url2 = createString("file_url2");

    public final StringPath file_url3 = createString("file_url3");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.heyhong.HeyHong.users.entity.NoticeScrap, com.heyhong.HeyHong.users.entity.QNoticeScrap> noticeScraps = this.<com.heyhong.HeyHong.users.entity.NoticeScrap, com.heyhong.HeyHong.users.entity.QNoticeScrap>createList("noticeScraps", com.heyhong.HeyHong.users.entity.NoticeScrap.class, com.heyhong.HeyHong.users.entity.QNoticeScrap.class, PathInits.DIRECT2);

    public final QNoticeTag noticeTag;

    public final EnumPath<DepartmentNotice.Status> status = createEnum("status", DepartmentNotice.Status.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QDepartmentNotice(String variable) {
        this(DepartmentNotice.class, forVariable(variable), INITS);
    }

    public QDepartmentNotice(Path<? extends DepartmentNotice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDepartmentNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDepartmentNotice(PathMetadata metadata, PathInits inits) {
        this(DepartmentNotice.class, metadata, inits);
    }

    public QDepartmentNotice(Class<? extends DepartmentNotice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new com.heyhong.HeyHong.hongik.entity.QDepartment(forProperty("department"), inits.get("department")) : null;
        this.noticeTag = inits.isInitialized("noticeTag") ? new QNoticeTag(forProperty("noticeTag")) : null;
    }

}

