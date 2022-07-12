package com.heyhong.HeyHong.users.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.notice.entity.CouncilNotice;
import com.heyhong.HeyHong.notice.entity.DepartmentNotice;
import com.heyhong.HeyHong.notice.entity.SchoolNotice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeScrap extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_notice_id")
    private SchoolNotice schoolNotice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_notice_id")
    private DepartmentNotice departmentNotice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "council_notice_id")
    private CouncilNotice councilNotice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    public enum Status{
        INACTIVE, ACTIVE
    }
}
