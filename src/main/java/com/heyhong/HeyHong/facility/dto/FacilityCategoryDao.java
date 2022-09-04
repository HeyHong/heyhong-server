package com.heyhong.HeyHong.facility.dto;

import com.heyhong.HeyHong.facility.entity.Facility;
import com.heyhong.HeyHong.facility.entity.FacilityCategory;
import com.heyhong.HeyHong.users.entity.LikeFacilityCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityCategoryDao {

    private Long facilityCategoryPk;

    private String categoryGroup;

    private String name;

    private String imageUrl;

    private LikeFacilityCategory.Status likeStatus;
}
