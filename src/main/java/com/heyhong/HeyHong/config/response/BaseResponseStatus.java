package com.heyhong.HeyHong.config.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {
    /**
     * 2XX code
     */
    OK(true,HttpStatus.OK,"요청 성공"),
    CREATED(true, HttpStatus.CREATED, "생성 성공"),

    /**
     * 4XX code
     */
    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED, "허가되지 않은 접근입니다."),
    RELOGIN(false, HttpStatus.UNAUTHORIZED, "모든 토큰이 만료되었습니다. 다시 로그인해주세요."),
    FORBIDDEN(false, HttpStatus.FORBIDDEN, "잘못된 접근입니다. 리소스를 찾을 수 없습니다."),
    REQUEST_ERROR(false,HttpStatus.BAD_REQUEST, "input값에 문제가 있습니다. 파라미터를 확인해주세요."),


    /**
     * User 관련
     */
    EMPTY_JWT(false, HttpStatus.BAD_REQUEST, "JWT를 입력해주세요."),
    INVALID_JWT(false, HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT입니다."),
    VALID_USERID(true, HttpStatus.OK, "사용가능한 아이디입니다"),
    DUPLICATE_USER_ID(false, HttpStatus.CONFLICT,"중복된 아이디입니다"),
    VALID_NICKNAME(true, HttpStatus.OK, "사용가능한 닉네임입니다"),
    DUPLICATE_NICKNAME(false, HttpStatus.CONFLICT, "중복된 닉네임입니다"),
    DUPLICATE_USER_EMAIL(false, HttpStatus.CONFLICT,"중복된 이메일입니다"),
    DUPLICATE_USER_STUDENTID(false, HttpStatus.CONFLICT, "중복된 학번입니다. 가입 여부를 확인해주세요"),
    PASSWORD_NOT_MATCH(false, HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");


    private final boolean isSuccess;
    private final HttpStatus code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, HttpStatus code, String message){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
