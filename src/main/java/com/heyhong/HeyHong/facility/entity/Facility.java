package com.heyhong.HeyHong.facility.entity;

import com.heyhong.HeyHong.building.entity.Floor;
import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.users.entity.Users;
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
public class Facility extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facility_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="facility_category_id")
    private FacilityCategory facilityCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_facility_id")
    private Facility parent;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy="facility")
    private List<FacilityImage> facilityImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Facility> facilities = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facility")
    private List<FacilityComment> facilityComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name="name", length = 25, nullable = false)
    private String name;

    @Column(name = "location", length = 10, nullable = true)
    private String location;

    @Column(name = "thumbnail_image_url", columnDefinition = "TEXT")
    private String thumbnail_image_url;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "opening_hours", length = 100)
    private String opening_hours;

    @Column(name = "telephone1_name", length = 30)
    private String telephone1_name;

    @Column(name = "telephone1", length = 25)
    private String telephone1;

    @Column(name = "telephone2_name", length = 30)
    private String telephone2_name;

    @Column(name = "telephone2", length = 25)
    private String telephone2;

    @Column(name = "telephone3_name", length = 30)
    private String telephone3_name;

    @Column(name = "telephone3", length = 25)
    private String telephone3;

    @Column(name = "homepage1_name", length = 30)
    private String homepage1_name;

    @Column(name = "homepage1_url", columnDefinition = "TEXT")
    private String homepage1_url;

    @Column(name = "homepage2_name", length = 30)
    private String homepage2_name;

    @Column(name = "homepage2_url", columnDefinition = "TEXT")
    private String homepage2_url;

    @Column(name = "homepage3_name", length = 30)
    private String homepage3_name;

    @Column(name = "homepage3_url", columnDefinition = "TEXT")
    private String homepage3_url;

    public enum Status{
        INACTIVE, ACTIVE
    }
}
