package com.heyhong.HeyHong.users.service;

import com.heyhong.HeyHong.hongik.entity.College;
import com.heyhong.HeyHong.hongik.entity.Department;
import com.heyhong.HeyHong.hongik.repository.CollegeRepository;
import com.heyhong.HeyHong.hongik.repository.DepartmentRepository;
import com.heyhong.HeyHong.users.dto.CheckConfirmEmailReq;
import com.heyhong.HeyHong.users.dto.CollegeDeptDto;
import com.heyhong.HeyHong.users.dto.SignInReq;
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

import java.security.SecureRandom;
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
    private final AuthService authService;


    /**
     * 회원 entity 저장
     * @param user
     * @return Long
     */
    @Transactional(rollbackFor = Exception.class)
    public Long save(Users user){
        return usersRepository.save(user).getId();
    }

    /**
     * 회원가입 최종 제출
     * @param signInReq
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Long signIn(SignInReq signInReq) throws Exception{

        // 이메일 인증 여부 확인
        ConfirmationToken cToken = confirmationTokenRepository.findByIdAndEmail(signInReq.getConfirmPk(), signInReq.getEmail()).orElseThrow(() -> new NoSuchElementException("해당 이메일 인증 토큰이 존재하지 않거나 인증되지 않은 이메일입니다."));
        if(cToken.getStatus() != ConfirmationToken.Status.SUCCESS){
            throw new Exception("이메일 인증이 완료되지 않은 계정입니다.");
        }

        System.out.println("인증 토큰 가져오기 성공");
        // 학부, 학과 fetch
        College college = collegeRepository.findById(signInReq.getCollegePk()).orElseThrow(()->new NoSuchElementException("해당 대학이 존재하지 않습니다. client validation 확인"));
        Department department = departmentRepository.findById(signInReq.getDepartmentPk()).orElseThrow(()-> new NoSuchElementException("해당 과가 존재하지 않습니다. client validation 확인"));

        Users signInCompletedUser = usersRepository.save(Users.builder()
                .userId(signInReq.getUserId())
                .password(passwordEncode(signInReq.getPassword()))
                .nickname(signInReq.getNickname())
                .email(signInReq.getEmail())
                .college(college)
                .department(department).status(Users.Status.ACTIVE)
                .profileImageUrl("https://heyhong.s3.ap-northeast-2.amazonaws.com/user_image/basic_profile_image.png")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return signInCompletedUser.getId();
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
     * 비밀번호 변경
     * @param user
     * @param curPassword
     * @param newPassword
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(Users user, String curPassword, String newPassword) throws Exception{

        if(!passwordEncoder.matches(curPassword, user.getPassword())){
            throw new IllegalArgumentException("잘못된 현재 비밀번호 입니다. 비밀번호를 다시 한번 확인해주세요.");
        }

        user.setPassword(passwordEncode(newPassword));
        usersRepository.save(user);

    }


//    /**
//     * 로그인
//     * user db와 대조 / jwt 발급
//     * @param userId
//     * @param password
//     * @return String - jwt
//     * @throws IllegalArgumentException
//     */
//    public String login(String userId, String password) throws Exception{
//
//        Users user = usersRepository.findByUserId(userId).orElseThrow(()->new IllegalArgumentException("가입되지 않은 회원입니다."));
//
//        if(!passwordEncoder.matches(password, user.getPassword())){
//            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
//        }
//        return jwtTokenProvider.createToken(user.getUserId(), user.getRoles());
//    }


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
     * 회원가입 인증메일 전송
     * @param email
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createEmailConfirmationForSignUp(String email){

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
            String text = "안녕하세요 학우님! 헤이홍 회원가입을 환영합니다 \n" +
                    " 회원가입을 위한 인증번호 : \n" + confirmationToken.getConfirmKey() + "\n어플로 돌아가서 인증번호 입력 후, 홍대생 인증이 되면 빠르게 회원가입 진행해드리겠습니다!";

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


    /**
     * 회원가입 인증 메일 번호 확인
     * @param confirmPk
     * @param email
     * @param confirmCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean checkConfirmEmail(Long confirmPk, String email, String confirmCode){

//        ConfirmationToken token = confirmationTokenRepository.findByIdAndStatus(confirmPk, ConfirmationToken.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 인증 토큰이 존재하지 않습니다. confirmPk를 확인해주세요."));
        ConfirmationToken token = confirmationTokenRepository.findByIdAndEmailAndStatus(confirmPk, email, ConfirmationToken.Status.ACTIVE)
                .orElseThrow(()-> new NoSuchElementException("해당 인증 토큰이 존재하지 않습니다. confirmPk 혹은 email을 확인해주세요"));

//        if(!token.getEmail().equals(email)){
//            throw new IllegalArgumentException("이메일이 일치하지 않습니다");
//        }

        //인증 유효시간 체크
        if(!token.checkTokenStatus()){
            confirmationTokenRepository.save(token);
            throw new IllegalArgumentException("인증 유효시간이 만료되었습니다.");
        }

        if(token.getConfirmKey().equals(confirmCode)){
            token.useToken();
            confirmationTokenRepository.save(token);
            return true;
        }
        else{
            return false;
        }


    }

    /**
     * id 찾기 - 이메일
     * @param email
     * @return
     */
    public boolean findLostId(String email){

        if(!checkHongikEmailFormat(email)){
            throw new IllegalArgumentException("학교의 이메일 형식이 아닙니다");
        }

        Optional<Users> user = usersRepository.findByEmailAndStatus(email, Users.Status.ACTIVE);
        if(user.isPresent()){
            try{
                createEmailConfirmationForFindId(email, user.get().getUserId());
            }catch (Exception e){
                throw e;
            }
            return true;
        }

        return false;
    }


    /**
     * 비밀번호 찾기
     * @param userId
     * @param email
     */
    @Transactional(rollbackFor = Exception.class)
    public void findLostPassword(String userId, String email) throws Exception{
        Users user = usersRepository.findByUserIdAndEmailAndStatus(userId, email, Users.Status.ACTIVE).orElseThrow(()-> new NoSuchElementException("해당 회원이 존재하지 않습니다. ID 혹은 email을 확인해주세요."));

        String newPassword = generateRandomPassword(10);

        System.out.println(newPassword);

        try{
            user.setPassword(passwordEncode(newPassword));
            usersRepository.save(user);
            createEmailForFindPassword(email, newPassword);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 비밀번호 찾기 > 비밀번호 초기화 안내 이메일 전송
     * @param email
     * @param newPassword
     */
    private void createEmailForFindPassword(String email, String newPassword){


        try{
            String subject = "[헤이홍] 비밀번호 초기화";
            String text = "안녕하세요 학우님! 학우님의 헤이홍 비밀번호는 아래로 초기화되었습니다! \n" +
                    newPassword + " \n어플로 돌아가서 로그인 후 프로필에서 반드시 비밀번호 변경해주세요!";

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            emailService.sendEmail(mailMessage);

        }catch (Exception e){
            throw e;
        }

    }


    /**
     * 아이디 찾기 안내 이메일 전송
     * @param email
     * @param userId
     */
    private void createEmailConfirmationForFindId(String email, String userId){

         int userIdLen = userId.length();
         String convertedUserId = userId.substring(0, userIdLen-3) + "***";

        try{
            String subject = "[헤이홍] 아이디 찾기";
            String text = "안녕하세요 학우님! 학우님의 헤이홍 아이디는  \n" +
                     convertedUserId + "입니다. \n 어플로 돌아가서 로그인해주세요!";

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            emailService.sendEmail(mailMessage);

        }catch (Exception e){
            throw e;
        }

    }


    /**
     * 회원가입 - 학과/학부 리스트
     * @return
     */
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
     * 랜덤 비밀번호 생성
     * @param len 생성하고자하는 비밀번호 자릿수
     * @return
     */
    private String generateRandomPassword(int len){
        // ASCII 범위 – 영숫자(0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
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
