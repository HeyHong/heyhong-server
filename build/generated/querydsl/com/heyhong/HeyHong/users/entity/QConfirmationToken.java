package com.heyhong.HeyHong.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConfirmationToken is a Querydsl query type for ConfirmationToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConfirmationToken extends EntityPathBase<ConfirmationToken> {

    private static final long serialVersionUID = -1612498768L;

    public static final QConfirmationToken confirmationToken = new QConfirmationToken("confirmationToken");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final StringPath confirmKey = createString("confirmKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> expirationDateTime = createDateTime("expirationDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<ConfirmationToken.Status> status = createEnum("status", ConfirmationToken.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QConfirmationToken(String variable) {
        super(ConfirmationToken.class, forVariable(variable));
    }

    public QConfirmationToken(Path<? extends ConfirmationToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConfirmationToken(PathMetadata metadata) {
        super(ConfirmationToken.class, metadata);
    }

}

