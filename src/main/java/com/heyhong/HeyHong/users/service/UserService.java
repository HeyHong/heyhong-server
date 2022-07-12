package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.hongik.repository.CollegeRepository;
import com.heyhong.HeyHong.hongik.repository.DepartmentRepository;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.jwt.JwtTokenProvider;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
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

    /**
     * 회원가입
     * College, Department find / pw encode / 회원 생성
     * @param userId
     * @param password
     * @param nickname
     * @param studentId
     * @param email
     * @param collegeName
     * @param departmentName
     * @return Long - 회원 id(pk)
     * @throws IllegalArgumentException
     */
    public Long signIn(String userId, String password, String nickname, String studentId, String email, String collegeName, String departmentName) throws IllegalArgumentException{
        System.out.println("hello");
        College college = collegeRepository.findByName(collegeName).orElseThrow(()->new IllegalArgumentException("해당 대학이 존재하지 않습니다. client validation 확인"));
        Department department = departmentRepository.findByName(departmentName).orElseThrow(()-> new IllegalArgumentException("해당 과가 존재하지 않습니다. client validation 확인"));

        System.out.println("hi");
        return this.save(Users.builder()
                .userId(userId)
                .password(passwordEncode(password))
                .nickname(nickname)
                .studentId(studentId)
                .email(email)
                .college(college)
                .department(department)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }


    public boolean validateNickName(String studnetId){
        if(usersRepository.findByStudentId(studnetId) == null){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * 로그인
     * user db와 대조 / jwt 발급
     * @param userId
     * @param password
     * @return String - jwt
     * @throws IllegalArgumentException
     */
    public String login(String userId, String password) throws IllegalArgumentException{

        Users user = usersRepository.findByUserId(userId).orElseThrow(()->new IllegalArgumentException("가입되지 않은 회원입니다."));

        if(!passwordEncoder.matches(user.getPassword(), password)){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        return jwtTokenProvider.createToken(user.getUserId(), user.getRoles());
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
