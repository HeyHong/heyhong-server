package com.heyhong.HeyHong.users.jwt;


import com.heyhong.HeyHong.users.dto.LoginReq;
import com.heyhong.HeyHong.users.dto.SignInReq;
import com.heyhong.HeyHong.users.service.CustomUserDetailService;
import com.mysql.cj.log.Log;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

//    private final long accessExpireTime = 60 * 60 * 1000L; // 1시간
    private final long accessExpireTime = 1 * 60 * 1000L; // 1분

    private final long refreshExpireTme = 1 * 60 * 2000L; // 2분


    private final CustomUserDetailService customUserDetailService;

    /**
     * JWT Access Token 생성 및 반환
     * @param loginReq
     * @return jwt token
     */
    public String createAccessToken(LoginReq loginReq){
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", loginReq.getUserId());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpireTime);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }


    /**
     * Refresh Token 발급
     * @param loginReq
     * @return map
     */
    public Map<String, String> createRefreshToken(LoginReq loginReq){
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", loginReq.getUserId());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + refreshExpireTme);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String refreshTokenExpirationAt = simpleDateFormat.format(expiration);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("refreshToken", jwt);
        result.put("refreshTokenExpirationAt", refreshTokenExpirationAt);

        return result;
    }


    /**
     * Authentication 객체 생성 - 현재 접근하는 주체의 정보와 권한을 담은 인터페이스 -> SecurityContext에 저장(SecurityContextHolder에 의해 접근 가능)
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Token 해독하여 userId(payload) 산출
     * @param token
     * @return userId
     */
    public String getUserId(String token){
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId");
    }


    /**
     * JWT token 검사
     * @param request
     * @param authToken
     * @return
     */
    public boolean validateJwtToken(ServletRequest request, String authToken){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            request.setAttribute("exception", "MalformedJwtException");
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", "ExpiredJwtException");
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", "UnsupportedJwtException");
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", "IllegalArgumentException");
        }

        return false;
    }


}
