package com.heyhong.HeyHong.hongik.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.notice.entity.CouncilNotice;
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
public class Council extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "council_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "category", length = 15, nullable = false)
    private String category;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "logo_img_url", columnDefinition = "TEXT")
    private String logoImgUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "council")
    private List<CouncilNotice> councilNotices = new ArrayList<>();

    public enum Status{
        INACTIVE, ACTIVE
    }
}
