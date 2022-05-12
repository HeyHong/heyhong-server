package com.heyhong.HeyHong.notice.entity;

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
public class SchoolNoticeCategory extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_notice_category_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolNoticeCategory")
    private List<SchoolNotice> schoolNotices = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    public enum Status{
        INACTIVE, ACTIVE
    }
}
