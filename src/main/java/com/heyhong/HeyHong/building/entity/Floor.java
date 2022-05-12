package com.heyhong.HeyHong.building.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.facility.entity.Facility;
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
public class Floor extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_id")
    private Long id;

    @Column(name = "number", length = 10, nullable = false)
    private String number;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "floor")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "floor")
    private List<Facility> facilities = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private Users.Status status = Users.Status.ACTIVE;

    public enum Status{
        INACTIVE, ACTIVE
    }
}

