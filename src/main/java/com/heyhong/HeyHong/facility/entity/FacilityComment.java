package com.heyhong.HeyHong.facility.entity;

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
public class FacilityComment extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_facility_comment_id")
    private FacilityComment parent;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<FacilityComment> children = new ArrayList<>();

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private AnonymousStatus anonymous = AnonymousStatus.INACTIVE;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

    public enum Status{
        INACTIVE, ACTIVE
    }

    public enum AnonymousStatus{
        INACTIVE, ACTIVE
    }
}
