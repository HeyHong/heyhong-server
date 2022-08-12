package com.heyhong.HeyHong.facility.dto;

import com.heyhong.HeyHong.facility.entity.FacilityCategory;
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
public class FaciltyCategoryGroupDto {

    private String parentCategoryName;
    private List<FacilityCategoryDto> facilityCategoryList = new ArrayList<>();

    public FaciltyCategoryGroupDto(String parentCategoryName){
        this.parentCategoryName = parentCategoryName;
    }

    public void addFacilityCategory(FacilityCategoryDto nf){
        this.facilityCategoryList.add(nf);
    }
}
