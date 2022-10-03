package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes {
    private Long refreshIdx;

    public LoginRes(Long refreshIdx) {
        this.refreshIdx = refreshIdx;
    }
}
