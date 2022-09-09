package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetUpdateMemberDepartmentRes {

    private Long curUserCollegePk;
    private Long curUserDepartmentPk;

    private List<CollegeDeptDto> collegeDeptList;

    public GetUpdateMemberDepartmentRes(Long curUserCollegePk, Long curUserDepartmentPk, List<CollegeDeptDto> collegeDeptList){
        this.curUserCollegePk = curUserCollegePk;
        this.curUserDepartmentPk = curUserDepartmentPk;
        this.collegeDeptList = collegeDeptList;
    }
}
