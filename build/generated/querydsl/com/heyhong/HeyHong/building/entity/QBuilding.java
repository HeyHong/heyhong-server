package com.heyhong.HeyHong.building.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuilding is a Querydsl query type for Building
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuilding extends EntityPathBase<Building> {

    private static final long serialVersionUID = -605469642L;

    public static final QBuilding building = new QBuilding("building");

    public final com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity _super = new com.heyhong.HeyHong.config.auditing.QBaseAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath description = createString("description");

    public final ListPath<Floor, QFloor> floors = this.<Floor, QFloor>createList("floors", Floor.class, QFloor.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image_url = createString("image_url");

    public final StringPath name_eng = createString("name_eng");

    public final StringPath name_kor = createString("name_kor");

    public final EnumPath<Building.Status> status = createEnum("status", Building.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QBuilding(String variable) {
        super(Building.class, forVariable(variable));
    }

    public QBuilding(Path<? extends Building> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuilding(PathMetadata metadata) {
        super(Building.class, metadata);
    }

}

