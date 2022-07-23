package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.hongik.repository.CollegeRepository;
import com.heyhong.HeyHong.hongik.repository.DepartmentRepository;
import com.heyhong.HeyHong.users.dto.CheckConfirmEmailReq;
import com.heyhong.HeyHong.users.dto.CollegeDeptDto;
import com.heyhong.HeyHong.users.entity.ConfirmationToken;
import com.heyhong.HeyHong.users.entity.Users;
import com.heyhong.HeyHong.users.jwt.JwtTokenProvider;
import com.heyhong.HeyHong.users.repository.ConfirmationTokenRepository;
import com.heyhong.HeyHong.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;


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
     * @param collegePk
     * @param departmentPk
     * @return Long - 회원 id(pk)
     * @throws IllegalArgumentException
     */
    public Long signIn(String userId, String password, String nickname, String studentId, String email, Long collegePk, Long departmentPk) throws Exception{

        // 이메일 인증 여부 확인
        ConfirmationToken cToken = confirmationTokenRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("해당 이메일 인증 토큰이 존재하지 않습니다"));
        if(cToken.getStatus() != ConfirmationToken.Status.SUCCESS){
            throw new Exception("이메일 인증이 완료되지 않은 계정입니다.");
        }

        // 학부, 학과 fetch
        College college = collegeRepository.findById(collegePk).orElseThrow(()->new NoSuchElementException("해당 대학이 존재하지 않습니다. client validation 확인"));
        Department department = departmentRepository.findById(departmentPk).orElseThrow(()-> new NoSuchElementException("해당 과가 존재하지 않습니다. client validation 확인"));
        
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
     * 아이디 중복 체크
     * @param userID
     * @return 중복 x - true / 중복 - false
     */
    public boolean validateUserId(String userID){

        Optional<Users> user = usersRepository.findByUserId(userID);

        if(!user.isPresent()){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 닉네임 중복 체크
     * @param nickname
     * @return 중복 x - true / 중복 - false
     */
    public boolean validateNickname(String nickname){

        Optional<Users> user = usersRepository.findByNickname(nickname);

        if(!user.isPresent()){
            return true;
        }else{
            return false;
        }

    }


    /**
     * 인증메일 전송
     * @param email
     * @return
     */
    @Transactional
    public Long createEmailConfirmation(String email){

        if(!checkHongikEmailFormat(email)){
            throw new IllegalArgumentException("학우의 이메일이 아닙니다");
        }

        // 인증 번호 생성 및 인증 번호 저장
        ConfirmationToken confirmationToken = new ConfirmationToken(email);
        Long confirmationTokenPk;

        try{
            confirmationTokenPk = confirmationTokenRepository.save(confirmationToken).getId();
        }catch (Exception e){
            throw e;
        }

        try{

            String subject = "[헤이홍] 회원가입 인증";
            String text = "안녕하세요 학우님! 헤이홍 회원가입을 환영합니다 <br/>" +
                    " 회원가입을 위한 인증번호 : <b> " + confirmationToken.getConfirmKey() + "</b><br/> 어플로 돌아가서 인증번호 입력 후, 홍대생 인증이 되면 빠르게 회원가입 진행해드리겠습니다!";

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            emailService.sendEmail(mailMessage);

        }catch (Exception e){
            throw e;
        }

        //confirmationToken 저장되어있는 pk값 반환
        return confirmationTokenPk;

    }



    @Transactional
    public boolean checkConfirmEmail(Long confirmPk, String email, String confirmCode){

        Optional<ConfirmationToken> token = confirmationTokenRepository.findByIdAndStatus(confirmPk, 1);
        if(!token.isPresent()){
            throw new NoSuchElementException("해당 토큰이 존재하지 않습니다");
        }

        if(!token.get().getEmail().equals(email)){
            throw new IllegalArgumentException("이메일이 일치하지 않습니다");
        }

        //인증 유효시간 체크
        if(!token.get().checkTokenStatus()){
            confirmationTokenRepository.save(token.get());
            throw new IllegalArgumentException("인증 유효시간이 만료되었습니다.");
        }

        if(token.get().getConfirmKey().equals(confirmCode)){
            token.get().useToken();
            confirmationTokenRepository.save(token.get());
            return true;
        }
        else{
            return false;
        }


    }



    public List<CollegeDeptDto> getCollegeDeptList(){
        List<College> colleges = collegeRepository.findAll();
        List<CollegeDeptDto> collegeDepts = new ArrayList<>();
        for(College c : colleges){
            collegeDepts.add(new CollegeDeptDto(c.getName(), c.getId()));
        }

        List<Department> departments = departmentRepository.findAll();
        for(Department d : departments){
            Long deptCollegePk = d.getCollege().getId();
            for(CollegeDeptDto cd : collegeDepts){
                if(cd.getCollegePk() == deptCollegePk){
                    cd.addDepartment(d.getName(), d.getId());
                    break;
                }
            }
        }

        return collegeDepts;
    }


    /**
     * password 인코딩
     * @param password
     * @return String - 인코딩된 pw
     */
    private String passwordEncode(String password){
        return passwordEncoder.encode(password);
    }


    /**
     * 이메일 도메인 확인 - 홍익대학교
     * @param email
     * @return
     */
    private boolean checkHongikEmailFormat(String email){
        String[] tokens = email.split("@");

        if(tokens[1].equals("g.hongik.ac.kr")){
            return true;
        }
        else{
            return false;
        }

    }


}
