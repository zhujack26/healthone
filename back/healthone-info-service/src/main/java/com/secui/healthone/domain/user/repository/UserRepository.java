package com.secui.healthone.domain.user.repository;

import com.secui.healthone.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
