package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollegeDeptDto {

    private String college;
    private Long collegePk;

    private List<DepartmentDto> departments;


    public CollegeDeptDto(String college, Long collegePk){
        this.college = college;
        this.collegePk = collegePk;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(String department, Long departmentPk){
        DepartmentDto nDept = new DepartmentDto(department, departmentPk);

        this.departments.add(nDept);
    }

}


