package com.heyhong.HeyHong.users.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseAuditingEntity {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String nickname;

    private String studentId;

    private String email;

    public enum Status{
        ACTIVE, INACTIVE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="college_id")
    private College college;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
