package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeTag is a Querydsl query type for NoticeTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeTag extends EntityPathBase<NoticeTag> {

    private static final long serialVersionUID = 1672210556L;

    public static final QNoticeTag noticeTag = new QNoticeTag("noticeTag");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final ListPath<CouncilNotice, QCouncilNotice> councilNotices = this.<CouncilNotice, QCouncilNotice>createList("councilNotices", CouncilNotice.class, QCouncilNotice.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final ListPath<DepartmentNotice, QDepartmentNotice> departmentNotices = this.<DepartmentNotice, QDepartmentNotice>createList("departmentNotices", DepartmentNotice.class, QDepartmentNotice.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<SchoolNotice, QSchoolNotice> schoolNotices = this.<SchoolNotice, QSchoolNotice>createList("schoolNotices", SchoolNotice.class, QSchoolNotice.class, PathInits.DIRECT2);

    public final EnumPath<NoticeTag.Status> status = createEnum("status", NoticeTag.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QNoticeTag(String variable) {
        super(NoticeTag.class, forVariable(variable));
    }

    public QNoticeTag(Path<? extends NoticeTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeTag(PathMetadata metadata) {
        super(NoticeTag.class, metadata);
    }

}

