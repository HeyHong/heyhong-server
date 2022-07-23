package com.heyhong.HeyHong.users.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckConfirmEmailReq {

    private Long confirmPk;
    private String email;
    private String confirmCode;
}
