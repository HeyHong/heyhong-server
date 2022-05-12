package com.heyhong.HeyHong.notice.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.hongik.entity.Council;
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
public class CouncilNotice extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "council_notice_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "title", length = 300, nullable = false)
    private String title;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

    @Column(name = "file_url1", columnDefinition = "TEXT")
    private String file_url1;

    @Column(name = "file_url2", columnDefinition = "TEXT")
    private String file_url2;

    @Column(name = "file_url3", columnDefinition = "TEXT")
    private String file_url3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "council_id")
    private Council council;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "councilNotice")
    private List<CouncilNoticeImage> councilNoticeImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_tag_id")
    private NoticeTag noticeTag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "councilNotice")
    private List<NoticeScrap> noticeScraps = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }
}
