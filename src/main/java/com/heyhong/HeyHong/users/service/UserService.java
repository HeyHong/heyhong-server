package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.hongik.repository.CollegeRepository;
import com.heyhong.HeyHong.hongik.repository.DepartmentRepository;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * 회원 entity 저장
     * @param user
     * @return Long
     */
    public Long save(Users user){
        return usersRepository.save(user).getId();
    }

    public Long signIn(String userId, String password, String nickname, String studentId, String email, String collegeName, String departmentName) throws Exception{

        College college = collegeRepository.findByName(collegeName).orElseThrow(()->new IllegalArgumentException("해당 대학이 존재하지 않습니다. client validation 확인"));
        Department department = departmentRepository.findByName(departmentName).orElseThrow(()-> new IllegalArgumentException("해당 과가 존재하지 않습니다. client validation 확인"));


        return UsersRepository.save(Users.builder()
                .userId(userId)
                .password(passwordEncode(password))
                .nickname(nickname)
                .studentId(studentId)
                .email(email)
                .college(college)
                .department(department)
                .build()).getId();
    }

    /**
     * password 인코딩
     * @param password
     * @return String - 인코딩된 pw
     */
    private String passwordEncode(String password){
        return passwordEncoder.encode(password);
    }


}
