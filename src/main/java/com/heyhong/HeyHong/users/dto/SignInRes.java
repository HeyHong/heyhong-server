package com.heyhong.HeyHong.users.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class SignInRes {
    private Long pk;
    private String jwt;

    public SignInRes(Long pk, String jwt){
        this.pk = pk;
        this.jwt = jwt;
    }
}
