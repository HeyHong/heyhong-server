package com.heyhong.HeyHong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@AllArgsConstructor
public class SignInReq {
    private String userId;
    private String password;
    private String nickname;
    private String studentId;
    private String email;
    private String college;
    private String department;
}
