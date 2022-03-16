package com.heyhong.HeyHong.users.repository;

import com.heyhong.HeyHong.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
