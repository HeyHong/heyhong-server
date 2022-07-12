package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.users.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

}
