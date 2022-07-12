package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchoolNotice is a Querydsl query type for SchoolNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchoolNotice extends EntityPathBase<SchoolNotice> {

    private static final long serialVersionUID = 1534462546L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchoolNotice schoolNotice = new QSchoolNotice("schoolNotice");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath notice_url = createString("notice_url");

    public final ListPath<com.heyhong.HeyHong.users.entity.NoticeScrap, com.heyhong.HeyHong.users.entity.QNoticeScrap> noticeScraps = this.<com.heyhong.HeyHong.users.entity.NoticeScrap, com.heyhong.HeyHong.users.entity.QNoticeScrap>createList("noticeScraps", com.heyhong.HeyHong.users.entity.NoticeScrap.class, com.heyhong.HeyHong.users.entity.QNoticeScrap.class, PathInits.DIRECT2);

    public final QNoticeTag noticeTag;

    public final QSchoolNoticeCategory schoolNoticeCategory;

    public final ListPath<SchoolNoticeComment, QSchoolNoticeComment> schoolNoticeComments = this.<SchoolNoticeComment, QSchoolNoticeComment>createList("schoolNoticeComments", SchoolNoticeComment.class, QSchoolNoticeComment.class, PathInits.DIRECT2);

    public final EnumPath<SchoolNotice.Status> status = createEnum("status", SchoolNotice.Status.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QSchoolNotice(String variable) {
        this(SchoolNotice.class, forVariable(variable), INITS);
    }

    public QSchoolNotice(Path<? extends SchoolNotice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchoolNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchoolNotice(PathMetadata metadata, PathInits inits) {
        this(SchoolNotice.class, metadata, inits);
    }

    public QSchoolNotice(Class<? extends SchoolNotice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.noticeTag = inits.isInitialized("noticeTag") ? new QNoticeTag(forProperty("noticeTag")) : null;
        this.schoolNoticeCategory = inits.isInitialized("schoolNoticeCategory") ? new QSchoolNoticeCategory(forProperty("schoolNoticeCategory")) : null;
    }

}

