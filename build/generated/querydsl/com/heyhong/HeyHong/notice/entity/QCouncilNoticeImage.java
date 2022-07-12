package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouncilNoticeImage is a Querydsl query type for CouncilNoticeImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouncilNoticeImage extends EntityPathBase<CouncilNoticeImage> {

    private static final long serialVersionUID = -1755329944L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCouncilNoticeImage councilNoticeImage = new QCouncilNoticeImage("councilNoticeImage");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final QCouncilNotice councilNotice;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final EnumPath<CouncilNoticeImage.Status> status = createEnum("status", CouncilNoticeImage.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QCouncilNoticeImage(String variable) {
        this(CouncilNoticeImage.class, forVariable(variable), INITS);
    }

    public QCouncilNoticeImage(Path<? extends CouncilNoticeImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCouncilNoticeImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCouncilNoticeImage(PathMetadata metadata, PathInits inits) {
        this(CouncilNoticeImage.class, metadata, inits);
    }

    public QCouncilNoticeImage(Class<? extends CouncilNoticeImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.councilNotice = inits.isInitialized("councilNotice") ? new QCouncilNotice(forProperty("councilNotice"), inits.get("councilNotice")) : null;
    }

}

