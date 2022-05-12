package com.heyhong.HeyHong.notice.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.users.entity.NoticeScrap;
import com.heyhong.HeyHong.users.entity.Users;
import jdk.jfr.MemoryAddress;
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
public class DepartmentNotice extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_notice_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "title", nullable = false, length = 300)
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
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentNotice")
    private List<DepartmentNoticeComment> departmentNoticeComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_tag_id")
    private NoticeTag noticeTag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentNotice")
    private List<NoticeScrap> noticeScraps = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }

}
