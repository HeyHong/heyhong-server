package com.heyhong.HeyHong.notice.entity;


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
public class NoticeTag extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_tag_id")

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeTag")
    private List<SchoolNotice> schoolNotices = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeTag")
    private List<DepartmentNotice> departmentNotices = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeTag")
    private List<CouncilNotice> councilNotices = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }

}
