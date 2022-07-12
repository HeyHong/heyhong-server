package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchoolNoticeCategory is a Querydsl query type for SchoolNoticeCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchoolNoticeCategory extends EntityPathBase<SchoolNoticeCategory> {

    private static final long serialVersionUID = -1231767184L;

    public static final QSchoolNoticeCategory schoolNoticeCategory = new QSchoolNoticeCategory("schoolNoticeCategory");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<SchoolNotice, QSchoolNotice> schoolNotices = this.<SchoolNotice, QSchoolNotice>createList("schoolNotices", SchoolNotice.class, QSchoolNotice.class, PathInits.DIRECT2);

    public final EnumPath<SchoolNoticeCategory.Status> status = createEnum("status", SchoolNoticeCategory.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QSchoolNoticeCategory(String variable) {
        super(SchoolNoticeCategory.class, forVariable(variable));
    }

    public QSchoolNoticeCategory(Path<? extends SchoolNoticeCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSchoolNoticeCategory(PathMetadata metadata) {
        super(SchoolNoticeCategory.class, metadata);
    }

}

