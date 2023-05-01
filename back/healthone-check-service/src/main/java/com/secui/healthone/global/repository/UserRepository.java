package com.secui.healthone.global.repository;

import com.secui.healthone.global.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
