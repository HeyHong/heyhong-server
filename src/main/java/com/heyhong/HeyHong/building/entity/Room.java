package com.heyhong.HeyHong.building.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
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
public class Room extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    @Column(name = "number", length = 10, nullable = false)
    private String number;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "opening_hours", length = 100)
    private String opening_hours;

    @Column(name = "telephone1_name", length = 30)
    private String telephone1_name;

    @Column(name = "telephone1", length = 25)
    private String telephone1;

    @Column(name = "telephone2_name", length = 30)
    private String telephone2_name;

    @Column(name = "telephone2", length = 25)
    private String telephone2;

    @Column(name = "telephone3_name", length = 30)
    private String telephone3_name;

    @Column(name = "telephone3", length = 25)
    private String telephone3;

    @Column(name = "homepage_name", length = 30)
    private String homepage_name;

    @Column(name = "homepage_url", columnDefinition = "TEXT")
    private String homepage_url;

    public enum Status{
        INACTIVE, ACTIVE
    }


}
