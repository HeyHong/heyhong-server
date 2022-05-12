package com.heyhong.HeyHong.building.entity;

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
public class RoomComment extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_room_comment_id")
    private RoomComment parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<RoomComment> children = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

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
