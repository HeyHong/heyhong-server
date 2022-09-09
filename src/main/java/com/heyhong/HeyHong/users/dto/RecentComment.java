package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecentComment {

    private String facilityCategory;
    private String facility;
    private Long facilityPk;
    private String contents;
//    private String createdAt;
}
