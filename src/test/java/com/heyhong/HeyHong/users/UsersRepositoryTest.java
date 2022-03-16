package com.heyhong.HeyHong.users;

import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("Auditing 기능 적용")
    public void insertUsers(){
        //given
        Users user = new Users();
        // when
        Users savedUser = usersRepository.save(user);

        // then
        System.out.println(savedUser.getCreatedAt());
        System.out.println(savedUser.getUpdatedAt());
    }

    @Test
    @Transactional
    @DisplayName("Auditing 기능 적용 - 수정")
    public void updateUsers(){
        //given
        Optional<Users> user = usersRepository.findById(1L);
        // when
        user.get().setNickname("hey");

        // then
        System.out.println(user.get().getCreatedAt());
        System.out.println(user.get().getUpdatedAt());
    }

}
