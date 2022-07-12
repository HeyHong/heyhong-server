package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouncilNotice is a Querydsl query type for CouncilNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouncilNotice extends EntityPathBase<CouncilNotice> {

    private static final long serialVersionUID = -229431629L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCouncilNotice councilNotice = new QCouncilNotice("councilNotice");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final StringPath contents = createString("contents");

    public final com.heyhong.HeyHong.hongik.entity.QCouncil council;

    public final ListPath<CouncilNoticeImage, QCouncilNoticeImage> councilNoticeImages = this.<CouncilNoticeImage, QCouncilNoticeImage>createList("councilNoticeImages", CouncilNoticeImage.class, QCouncilNoticeImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath file_url1 = createString("file_url1");

    public final StringPath file_url2 = createString("file_url2");

    public final StringPath file_url3 = createString("file_url3");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.heyhong.HeyHong.users.entity.NoticeScrap, com.heyhong.HeyHong.users.entity.QNoticeScrap> noticeScraps = this.<com.heyhong.HeyHong.users.entity.NoticeScrap, com.heyhong.HeyHong.users.entity.QNoticeScrap>createList("noticeScraps", com.heyhong.HeyHong.users.entity.NoticeScrap.class, com.heyhong.HeyHong.users.entity.QNoticeScrap.class, PathInits.DIRECT2);

    public final QNoticeTag noticeTag;

    public final EnumPath<CouncilNotice.Status> status = createEnum("status", CouncilNotice.Status.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QCouncilNotice(String variable) {
        this(CouncilNotice.class, forVariable(variable), INITS);
    }

    public QCouncilNotice(Path<? extends CouncilNotice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCouncilNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCouncilNotice(PathMetadata metadata, PathInits inits) {
        this(CouncilNotice.class, metadata, inits);
    }

    public QCouncilNotice(Class<? extends CouncilNotice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.council = inits.isInitialized("council") ? new com.heyhong.HeyHong.hongik.entity.QCouncil(forProperty("council")) : null;
        this.noticeTag = inits.isInitialized("noticeTag") ? new QNoticeTag(forProperty("noticeTag")) : null;
    }

}

