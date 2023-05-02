package com.secui.healthone.domain.sleep.repository;

import com.secui.healthone.domain.sleep.entity.Sleep;
import com.secui.healthone.global.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SleepRepository extends JpaRepository<Sleep, Integer> {
    List<Sleep> findAllByUserAndSleepCreatetimeLike(User user, String date);
}
