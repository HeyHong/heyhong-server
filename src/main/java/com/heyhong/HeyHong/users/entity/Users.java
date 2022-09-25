package com.heyhong.HeyHong.users.entity;

import com.heyhong.HeyHong.building.entity.RoomComment;
import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import com.heyhong.HeyHong.facility.entity.FacilityComment;
import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.notice.entity.DepartmentNoticeComment;
import com.heyhong.HeyHong.notice.entity.SchoolNoticeComment;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends BaseAuditingEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="id")
    private String userId;

    private String password;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    private String nickname;

    private String studentId;

    private String email;

    private String jwtToken;

    public enum Status{
        INACTIVE, ACTIVE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="college_id")
    private College college;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<RoomComment> roomComments = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<FacilityComment> facilityComments = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<SchoolNoticeComment> schoolNoticeComments = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<DepartmentNoticeComment> departmentNoticeComments = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<NoticeScrap> noticeScraps = new ArrayList<>();

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @ElementCollection(fetch=FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername(){
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setCollegeAndDepartment(College college, Department department){
        this.college = college;
        this.department = department;
    }

    public void setJwtToken(String jwtToken){
        this.jwtToken = jwtToken;
    }

    public String getJwtToken(){
        return this.jwtToken;
    }

}
