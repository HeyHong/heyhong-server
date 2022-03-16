package com.heyhong.HeyHong.hongik.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="college")
@Getter
public class College extends BaseAuditingEntity {

    @Id
    @Column(name = "college_id")
    @GeneratedValue
    private Long id;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "college")
    private List<Department> departments = new ArrayList<>();


}
