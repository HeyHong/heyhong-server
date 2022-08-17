package com.heyhong.HeyHong.config.response;

import com.amazonaws.SdkBaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class BaseResponse<T> {

    private final Boolean isSuccess;
    private final int code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    /*
    올바른 response인 경우
     */
    public BaseResponse(BaseResponseStatus status, T result){
        this.isSuccess = true;
        this.code = status.getCode().value();
        this.message = status.getMessage();
        this.result = result;
    }

    /*
    Exception인 경우
     */
    public BaseResponse(BaseResponseStatus status){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode().value();
        this.message = status.getMessage();
    }

    public BaseResponse(BaseResponseStatus status, String message){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode().value();
        this.message = message;
    }


}
