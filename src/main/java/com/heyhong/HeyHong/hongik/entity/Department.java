package com.heyhong.HeyHong.hongik.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.notice.entity.DepartmentNotice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseAuditingEntity {

    @Id
    @Column(name = "department_id")
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    private College college;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<DepartmentNotice> departmentNotices = new ArrayList<>();


}
