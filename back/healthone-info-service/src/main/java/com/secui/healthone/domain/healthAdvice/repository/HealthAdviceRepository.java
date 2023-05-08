package com.secui.healthone.domain.healthAdvice.repository;

import com.secui.healthone.domain.healthAdvice.entity.HealthAdvice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthAdviceRepository extends JpaRepository<HealthAdvice, Integer> {
    List<HealthAdvice> findByCreatetimeAndUserNo(LocalDateTime localDateTime, int userNo);
}
