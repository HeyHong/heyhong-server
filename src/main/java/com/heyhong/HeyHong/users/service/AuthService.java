package com.heyhong.HeyHong.users.service;


import com.heyhong.HeyHong.users.dto.LoginReq;
import com.heyhong.HeyHong.users.dto.LoginRes;
import com.heyhong.HeyHong.users.dto.UpdateAccessTokenReq;
import com.heyhong.HeyHong.users.dto.UpdateAccessTokenRes;
import com.heyhong.HeyHong.users.entity.RefreshToken;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.jwt.JwtProvider;
import com.heyhong.HeyHong.users.repository.RefreshTokenRepository;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public LoginRes login(LoginReq loginReq) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUserId(), loginReq.getPassword()));

            Map createdToken = createToken(loginReq);

            Users user = usersRepository.findByUserIdAndStatus(loginReq.getUserId(), Users.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("로그인 회원 2차 조회 시 결과, 해당 회원이 존재하지 않습니다."));
//            user.setJwtToken(passwordEncoder.encode((String)createdToken.get("accessToken")));
            // JPA 변경감지
            user.setJwtToken((String)createdToken.get("accessToken"));

            return new LoginRes((String)createdToken.get("accessToken"), (Long)createdToken.get("refreshIdx"));
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("비밀번호 혹은 아이디가 잘못되었습니다");
        }
    }


    public UpdateAccessTokenRes newAccessToken(UpdateAccessTokenReq updateAccessTokenReq, HttpServletRequest request) throws Exception{
        Map result = new HashMap();
        Optional<RefreshToken> refreshTokenEntity = Optional.ofNullable(refreshTokenRepository.findById(updateAccessTokenReq.getRefreshIdx())
                .orElseThrow(() -> new Exception("refresh token 이 존재하지 않습니다")));

        String refreshToken = refreshTokenEntity.get().getRefreshToken();

        //accessToken은 만료되었으나, refresh token이 만료되지 않은 경우
        if(jwtProvider.validateJwtToken(request, refreshToken)){
            String userId = jwtProvider.getUserInfo(refreshToken);
            LoginReq tmpLoginReq = new LoginReq();
            tmpLoginReq.setUserId(userId);

            Map createdToken = createToken(tmpLoginReq);

            return new UpdateAccessTokenRes((String)createdToken.get("accessToken"), (Long)createdToken.get("refreshIdx"));

        }
        //refreshtoken 자체가 만료된 경우에는 재로그인 진행해야
        else{
            throw new Exception("refresh toKen 만료. 재로그인 필요합니다");
        }
    }


    //토큰을 생성 후 변환
    private Map<String, String> createToken(LoginReq loginReq){
        Map result = new HashMap();

        String accessToken = jwtProvider.createAccessToken(loginReq);
        // createRefreshToken 자체가 Map을 return
        Map<String, String> refreshTokenMap = jwtProvider.createRefreshToken(loginReq);
        String refreshToken = refreshTokenMap.get("refreshToken");
        String refreshTokenExpirationAt = refreshTokenMap.get("refreshTokenExpirationAt");

        RefreshToken refreshTokenEntity = RefreshToken
                .builder()
                .userId(loginReq.getUserId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationAt(refreshTokenExpirationAt)
                .build();

        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshTokenEntity);


        result.put("accessToken", accessToken);
        result.put("refreshIdx", savedRefreshToken.getId());

        return result;
    }
}
