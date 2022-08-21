package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(String userId);
    Optional<Users> findByStudentId(String studentId);
    Optional<Users> findByNickname(String nickname);
    Optional<Users> findByEmailAndStatus(String email, Users.Status status);
    boolean existsByEmailAndStatus(String email, Users.Status status);

}
