package com.heyhong.HeyHong.config.security;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.heyhong.HeyHong.config.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException{
        String exception = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        System.out.println("--------COMMENCE-------");
        /**
         * 토큰이 없는 경우 예외 처리
         */

        if(exception == null){
            errorCode = ErrorCode.UNAUTHORIZEDException;
            setResponse(response, errorCode);
            return;
        }

        /**
         * 토큰이 만료된 경우 예외처리
         */
        if(exception.equals("ExpiredJwtException")){
            errorCode = ErrorCode.ExpiredJwtException;
            setResponse(response, errorCode);
            return;
        }

        /**
         * 토큰의 형식이 잘못된 경우
         */
        if(exception.equals("MalformedJwtException")){
            System.out.println(">>>>이상한 토큰<<<<");
            errorCode = ErrorCode.MalformedJwtException;
            setResponse(response, errorCode);
            return;
        }

        if(exception.equals("UnsupportedJwtException") || exception.equals("IllegalArgumentException")){
            System.out.println(">>>>토큰 오류<<<<");
            errorCode = ErrorCode.MalformedJwtException;
            setResponse(response, errorCode);
            return;
        }



    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException{
        JSONObject json = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        json.put("code", errorCode.getCode());
        json.put("message", errorCode.getMessage());
        response.getWriter().print(json);
    }

}
