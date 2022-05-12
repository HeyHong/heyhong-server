package com.heyhong.HeyHong.facility.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityCategory extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facility_category_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name="name", length = 25, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityCategory")
    private List<Facility> facilities = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }

}
