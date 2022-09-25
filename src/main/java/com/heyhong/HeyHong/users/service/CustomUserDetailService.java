package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
        return usersRepository.findByUserIdAndStatus(userId, Users.Status.ACTIVE).orElseThrow(() -> new UsernameNotFoundException("user를 찾을 수 없습니다"));
    }

}
