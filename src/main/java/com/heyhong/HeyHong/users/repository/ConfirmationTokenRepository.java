package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.users.entity.ConfirmationToken;
import com.heyhong.HeyHong.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByIdAndEmailAndStatus(Long id, String email, ConfirmationToken.Status status);
    Optional<ConfirmationToken> findByIdAndEmail(Long id, String email);

}
