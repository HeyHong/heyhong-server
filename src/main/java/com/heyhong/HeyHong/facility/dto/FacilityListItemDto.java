package com.heyhong.HeyHong.facility.dto;

import com.heyhong.HeyHong.facility.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityListItemDto {

    private Long facilityPk;
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private int commentCount;

    public FacilityListItemDto(Facility f){
        this.facilityPk = f.getId();
        this.name = f.getName();
        this.location = f.getLocation();
        this.description = f.getDescription();
        this.imageUrl = f.getImage_url();
        this.commentCount = 0;
    }

    public FacilityListItemDto(Facility f, Long count){
        this.facilityPk = f.getId();
        this.name = f.getName();
        this.location = f.getLocation();
        this.description = f.getDescription();
        this.imageUrl = f.getImage_url();
        this.commentCount = count.intValue();
    }

}
