package com.heyhong.HeyHong.facility.dto;

import com.heyhong.HeyHong.facility.entity.Facility;
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
public class FacilityDetailRes {

    private boolean likeStatus;
    private String name;
    private String location;
    private Long floorPk;
    private String floorImageUrl;
    private String description;
    private List<String> images = new ArrayList<>();
    private String opening_hours;
    private String telephone1_name;
    private String telephone1;
    private String telephone2_name;
    private String telephone2;
    private String telephone3_name;
    private String telephone3;
    private String homepage1_name;
    private String homepage1_url;
    private String homepage2_name;
    private String homepage2_url;
    private String homepage3_name;
    private String homepage3_url;

//    private List<FacilityDetailRes> subFacilities = new ArrayList<>();

    public FacilityDetailRes(Facility f){
        this.name = f.getName();
        this.location = f.getLocation();
        this.floorPk = null;
        this.floorImageUrl = null;
        this.description = f.getDescription();

        this.opening_hours = f.getOpening_hours();
        this.telephone1_name = f.getTelephone1_name();
        this.telephone1 = f.getTelephone1();
        this.telephone2_name = f.getTelephone2_name();
        this.telephone2 = f.getTelephone2();
        this.telephone3_name = f.getTelephone3_name();
        this.telephone3 = f.getTelephone3();
        this.homepage1_name = f.getHomepage1_name();
        this.homepage1_url = f.getHomepage1_url();
        this.homepage2_name = f.getHomepage2_name();
        this.homepage2_url = f.getHomepage2_url();
        this.homepage3_name = f.getHomepage3_name();
        this.homepage3_url = f.getHomepage3_url();
    }

    public FacilityDetailRes(Facility f, Long floorPk, String floorImageUrl){
        this.name = f.getName();
        this.location = f.getLocation();
        this.floorPk = floorPk;
        this.floorImageUrl = floorImageUrl;
        this.description = f.getDescription();

        this.opening_hours = f.getOpening_hours();
        this.telephone1_name = f.getTelephone1_name();
        this.telephone1 = f.getTelephone1();
        this.telephone2_name = f.getTelephone2_name();
        this.telephone2 = f.getTelephone2();
        this.telephone3_name = f.getTelephone3_name();
        this.telephone3 = f.getTelephone3();
        this.homepage1_name = f.getHomepage1_name();
        this.homepage1_url = f.getHomepage1_url();
        this.homepage2_name = f.getHomepage2_name();
        this.homepage2_url = f.getHomepage2_url();
        this.homepage3_name = f.getHomepage3_name();
        this.homepage3_url = f.getHomepage3_url();
    }

}
