package com.heyhong.HeyHong.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDepartmentNoticeComment is a Querydsl query type for DepartmentNoticeComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDepartmentNoticeComment extends EntityPathBase<DepartmentNoticeComment> {

    private static final long serialVersionUID = 1143353231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDepartmentNoticeComment departmentNoticeComment = new QDepartmentNoticeComment("departmentNoticeComment");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final ListPath<DepartmentNoticeComment, QDepartmentNoticeComment> children = this.<DepartmentNoticeComment, QDepartmentNoticeComment>createList("children", DepartmentNoticeComment.class, QDepartmentNoticeComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final QDepartmentNotice departmentNotice;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QDepartmentNoticeComment parent;

    public final EnumPath<DepartmentNoticeComment.Status> status = createEnum("status", DepartmentNoticeComment.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final com.heyhong.HeyHong.users.entity.QUsers user;

    public QDepartmentNoticeComment(String variable) {
        this(DepartmentNoticeComment.class, forVariable(variable), INITS);
    }

    public QDepartmentNoticeComment(Path<? extends DepartmentNoticeComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDepartmentNoticeComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDepartmentNoticeComment(PathMetadata metadata, PathInits inits) {
        this(DepartmentNoticeComment.class, metadata, inits);
    }

    public QDepartmentNoticeComment(Class<? extends DepartmentNoticeComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.departmentNotice = inits.isInitialized("departmentNotice") ? new QDepartmentNotice(forProperty("departmentNotice"), inits.get("departmentNotice")) : null;
        this.parent = inits.isInitialized("parent") ? new QDepartmentNoticeComment(forProperty("parent"), inits.get("parent")) : null;
        this.user = inits.isInitialized("user") ? new com.heyhong.HeyHong.users.entity.QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

