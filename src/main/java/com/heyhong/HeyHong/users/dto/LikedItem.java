package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikedItem {

    private LikeType type;
    private Long pk;
    private String name;

    public enum LikeType{
        facilityCategory, facility
    }
}
