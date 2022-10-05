package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes {
    private String accessToken;
    private Long refreshIdx;

    public LoginRes(String accessToken, Long refreshIdx) {
        this.accessToken = accessToken;
        this.refreshIdx = refreshIdx;
    }
}
