package com.heyhong.HeyHong.users.jwt;

import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse reponse, FilterChain chain) throws IOException, ServletException{
//
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//
//        if(token != null && jwtTokenProvider.validateToken(token)){
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        chain.doFilter(request, reponse);
//    }

    private final JwtProvider jwtProvider;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        // 헤더에서 JWT 가져옴
        String token = jwtProvider.resolveToken((HttpServletRequest) request);

        // 유효한 토큰인지 validate
        if(token != null && jwtProvider.validateJwtToken(request, token) && usersRepository.existsByUserIdAndJwtTokenAndStatus(jwtProvider.getUserInfo(token), token, Users.Status.ACTIVE)){

            // 유효시, 유져 정보 가져옴
            Authentication authentication = jwtProvider.getAuthentication(token);

            // SecurityContext에 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

//        if(token != null && jwtProvider.validateJwtToken(request, token)){
//
//            if(passwordEncoder.matches(token, usersRepository.findByUserIdAndStatus(jwtProvider.getUserInfo(token), Users.Status.ACTIVE).get().getJwtToken())){
//                // 유효시, 유져 정보 가져옴
//                Authentication authentication = jwtProvider.getAuthentication(token);
//
//                // SecurityContext에 Authentication 객체 저장
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//
//        }

        chain.doFilter(request, response);

    }



}
