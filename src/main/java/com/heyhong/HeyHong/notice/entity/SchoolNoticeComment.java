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
public class SchoolNoticeComment extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_notice_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_notice_id")
    private SchoolNotice schoolNotice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_school_notice_comment_id")
    private SchoolNoticeComment parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<SchoolNoticeComment> children = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

    public enum Status{
        INACTIVE, ACTIVE
    }

}
