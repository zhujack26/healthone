package com.secui.healthone.domain.heartRate.repository;

import com.secui.healthone.domain.heartRate.entity.HeartRate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HeartRateRepository extends JpaRepository<HeartRate, Integer> {
//    List<HeartRate> findAllByUserAndHeartRateCreatetimeBetween(User user, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<HeartRate> findAllByUserNoAndCreateTimeBetween(Integer userNo, LocalDateTime startDateTime, LocalDateTime endDateTime);

    Slice<HeartRate> findAllByUserNoOrderByCreateTimeDesc(Integer userNo, Pageable pageable);
}
