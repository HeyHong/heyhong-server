package com.heyhong.HeyHong.config.auditing;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseAuditingEntity is a Querydsl query type for BaseAuditingEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseAuditingEntity extends EntityPathBase<BaseAuditingEntity> {

    private static final long serialVersionUID = 745876071L;

    public static final QBaseAuditingEntity baseAuditingEntity = new QBaseAuditingEntity("baseAuditingEntity");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateAt = createDateTime("updateAt", java.time.LocalDateTime.class);

    public QBaseAuditingEntity(String variable) {
        super(BaseAuditingEntity.class, forVariable(variable));
    }

    public QBaseAuditingEntity(Path<? extends BaseAuditingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseAuditingEntity(PathMetadata metadata) {
        super(BaseAuditingEntity.class, metadata);
    }

}

