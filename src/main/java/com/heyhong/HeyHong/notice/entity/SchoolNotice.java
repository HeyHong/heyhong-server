package com.heyhong.HeyHong.notice.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.users.entity.NoticeScrap;
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
public class SchoolNotice extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_notice_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @Column(name = "notice_url", nullable = false, columnDefinition = "TEXT")
    private String notice_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_notice_category_id")
    private SchoolNoticeCategory schoolNoticeCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolNotice")
    private List<SchoolNoticeComment> schoolNoticeComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_tag_id")
    private NoticeTag noticeTag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolNotice")
    private List<NoticeScrap> noticeScraps = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }
}
