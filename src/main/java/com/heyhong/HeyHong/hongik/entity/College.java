package com.heyhong.HeyHong.hongik.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
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
public class College extends BaseAuditingEntity {

    @Id
    @Column(name = "college_id")
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "college")
    private List<Department> departments = new ArrayList<>();


}
