package com.secui.healthone.domain.heartRate.repository;

import com.secui.healthone.domain.heartRate.entity.HeartRate;
import com.secui.healthone.global.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HeartRateRepository extends JpaRepository<HeartRate, Integer> {
    List<HeartRate> findAllByUserAndHeartRateCreatetimeBetween(User user, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
