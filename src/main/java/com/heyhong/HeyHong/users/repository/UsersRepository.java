package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(String userId);
    Optional<Users> findByStudentId(String studentId);
}
