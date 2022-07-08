package com.heyhong.HeyHong.building.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
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
public class Building extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "name_eng", length = 5, nullable = false)
    private String name_eng;

    @Column(name = "name_kor", length = 15, nullable = false)
    private String name_kor;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String image_url;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
    private List<Floor> floors = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }

}
