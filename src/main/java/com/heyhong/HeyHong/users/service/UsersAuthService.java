package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersAuthService {

    @Autowired
    UsersRepository usersRepository;

    public Optional<Users> findByIdPw(String userId){
        return usersRepository.findByUserId(userId);
    }
}
