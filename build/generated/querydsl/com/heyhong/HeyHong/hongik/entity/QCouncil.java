package com.heyhong.HeyHong.hongik.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouncil is a Querydsl query type for Council
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouncil extends EntityPathBase<Council> {

    private static final long serialVersionUID = -1185062031L;

    public static final QCouncil council = new QCouncil("council");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final StringPath category = createString("category");

    public final ListPath<com.heyhong.HeyHong.notice.entity.CouncilNotice, com.heyhong.HeyHong.notice.entity.QCouncilNotice> councilNotices = this.<com.heyhong.HeyHong.notice.entity.CouncilNotice, com.heyhong.HeyHong.notice.entity.QCouncilNotice>createList("councilNotices", com.heyhong.HeyHong.notice.entity.CouncilNotice.class, com.heyhong.HeyHong.notice.entity.QCouncilNotice.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath logoImgUrl = createString("logoImgUrl");

    public final StringPath name = createString("name");

    public final EnumPath<Council.Status> status = createEnum("status", Council.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QCouncil(String variable) {
        super(Council.class, forVariable(variable));
    }

    public QCouncil(Path<? extends Council> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouncil(PathMetadata metadata) {
        super(Council.class, metadata);
    }

}

