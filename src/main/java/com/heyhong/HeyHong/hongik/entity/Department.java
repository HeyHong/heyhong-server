package com.heyhong.HeyHong.hongik.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Getter
public class Department extends BaseAuditingEntity {

    @Id
    @Column(name = "department_id")
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    private College college;


}
