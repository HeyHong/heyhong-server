package com.heyhong.HeyHong.facility.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityImage extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facility_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="facility_id")
    private Facility facility;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    public enum Status{
        INACTIVE, ACTIVE
    }

    public FacilityImage(Facility facility, String imageUrl){
        this.facility = facility;
        this.imageUrl = imageUrl;
        this.status = Status.ACTIVE;
    }
}
