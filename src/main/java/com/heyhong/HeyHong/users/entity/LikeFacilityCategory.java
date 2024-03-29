package com.heyhong.HeyHong.users.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeFacilityCategory extends BaseAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_facility_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_category_id")
    private FacilityCategory facilityCategory;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    public enum Status{
        INACTIVE, ACTIVE
    }

    public LikeFacilityCategory(Users user, FacilityCategory facilityCategory){
        this.user = user;
        this.facilityCategory = facilityCategory;
        this.status = Status.ACTIVE;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUserNullAndInactive(){
        this.user = null;
        this.status = Status.INACTIVE;
    }
}
