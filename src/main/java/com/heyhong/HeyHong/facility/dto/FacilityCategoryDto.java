package com.heyhong.HeyHong.facility.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityCategoryDto {

    private Long facilityCategoryPk;
    private String categoryName;
    private String imageUrl;
    private Boolean likeStatus;

    public FacilityCategoryDto(FacilityCategoryDao facilityCategoryListDto){
        this.facilityCategoryPk = facilityCategoryListDto.getFacilityCategoryPk();
        this.categoryName = facilityCategoryListDto.getName();
        this.imageUrl = facilityCategoryListDto.getImageUrl();
        if(facilityCategoryListDto.getLikeStatus() == null){
            this.likeStatus = false;
        }else{
            this.likeStatus = true;
        }
    }
}
