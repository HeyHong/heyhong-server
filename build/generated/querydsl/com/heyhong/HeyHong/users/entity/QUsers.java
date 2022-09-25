package com.heyhong.HeyHong.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -1803636236L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsers users = new QUsers("users");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    public final com.heyhong.HeyHong.hongik.entity.QCollege college;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final com.heyhong.HeyHong.hongik.entity.QDepartment department;

    public final ListPath<com.heyhong.HeyHong.notice.entity.DepartmentNoticeComment, com.heyhong.HeyHong.notice.entity.QDepartmentNoticeComment> departmentNoticeComments = this.<com.heyhong.HeyHong.notice.entity.DepartmentNoticeComment, com.heyhong.HeyHong.notice.entity.QDepartmentNoticeComment>createList("departmentNoticeComments", com.heyhong.HeyHong.notice.entity.DepartmentNoticeComment.class, com.heyhong.HeyHong.notice.entity.QDepartmentNoticeComment.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<com.heyhong.HeyHong.facility.entity.FacilityComment, com.heyhong.HeyHong.facility.entity.QFacilityComment> facilityComments = this.<com.heyhong.HeyHong.facility.entity.FacilityComment, com.heyhong.HeyHong.facility.entity.QFacilityComment>createList("facilityComments", com.heyhong.HeyHong.facility.entity.FacilityComment.class, com.heyhong.HeyHong.facility.entity.QFacilityComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jwtToken = createString("jwtToken");

    public final StringPath nickname = createString("nickname");

    public final ListPath<NoticeScrap, QNoticeScrap> noticeScraps = this.<NoticeScrap, QNoticeScrap>createList("noticeScraps", NoticeScrap.class, QNoticeScrap.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<com.heyhong.HeyHong.building.entity.RoomComment, com.heyhong.HeyHong.building.entity.QRoomComment> roomComments = this.<com.heyhong.HeyHong.building.entity.RoomComment, com.heyhong.HeyHong.building.entity.QRoomComment>createList("roomComments", com.heyhong.HeyHong.building.entity.RoomComment.class, com.heyhong.HeyHong.building.entity.QRoomComment.class, PathInits.DIRECT2);

    public final ListPath<com.heyhong.HeyHong.notice.entity.SchoolNoticeComment, com.heyhong.HeyHong.notice.entity.QSchoolNoticeComment> schoolNoticeComments = this.<com.heyhong.HeyHong.notice.entity.SchoolNoticeComment, com.heyhong.HeyHong.notice.entity.QSchoolNoticeComment>createList("schoolNoticeComments", com.heyhong.HeyHong.notice.entity.SchoolNoticeComment.class, com.heyhong.HeyHong.notice.entity.QSchoolNoticeComment.class, PathInits.DIRECT2);

    public final EnumPath<Users.Status> status = createEnum("status", Users.Status.class);

    public final StringPath studentId = createString("studentId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final StringPath userId = createString("userId");

    public QUsers(String variable) {
        this(Users.class, forVariable(variable), INITS);
    }

    public QUsers(Path<? extends Users> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsers(PathMetadata metadata, PathInits inits) {
        this(Users.class, metadata, inits);
    }

    public QUsers(Class<? extends Users> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.college = inits.isInitialized("college") ? new com.heyhong.HeyHong.hongik.entity.QCollege(forProperty("college")) : null;
        this.department = inits.isInitialized("department") ? new com.heyhong.HeyHong.hongik.entity.QDepartment(forProperty("department"), inits.get("department")) : null;
    }

}

