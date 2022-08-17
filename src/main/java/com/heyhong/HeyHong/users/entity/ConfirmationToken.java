package com.heyhong.HeyHong.users.entity;

import com.heyhong.HeyHong.config.auditing.BaseAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken extends BaseAuditingEntity {

    // 이메일 인증 토큰 유효시간 - 5분
    private static final long TOKEN_EXPIRATION_TIME_VALUE = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_token_id")
    private Long id;

    private String email;

    private String confirmKey;

    private LocalDateTime expirationDateTime;

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;

    public enum Status{
        INACTIVE, ACTIVE, SUCCESS
    }

    public ConfirmationToken(String email){
        this.email = email;
        this.confirmKey = this.generateKey();
        this.expirationDateTime = LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_TIME_VALUE);
        this.status = Status.ACTIVE;
    }

    /**
     * 인증키 생성
     * @return 0000 네자리 숫자의 string
     */
    private String generateKey(){
        Random random = new Random();
        String key = String.valueOf(random.nextInt(8888)+1111);
        return key;
    }

    /**
     * 인증키 사용
     */
    public void useToken(){
        this.status = Status.SUCCESS;
    }

    /**
     * 인증키 유효시간 체크 -> 만료 상태 변경
     * @return 유효시간 지나지 않은 경우 true / 지난 경우 false
     */
    public boolean checkTokenStatus(){
        // 아직 유효시간이 지나지 않은 경우
        if(LocalDateTime.now().isBefore(this.expirationDateTime)) {
            return true;
        }
        else {
        // 유효시간을 지난 경우
            this.status = Status.INACTIVE;
            return false;
        }
    }
}
