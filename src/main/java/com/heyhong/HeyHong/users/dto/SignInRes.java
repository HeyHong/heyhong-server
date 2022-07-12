package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRes {
    private Long pk;
    private LoginRes jwtInfo;

}
