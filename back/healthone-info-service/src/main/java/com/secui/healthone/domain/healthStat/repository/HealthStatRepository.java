package com.secui.healthone.domain.healthStat.repository;

import com.secui.healthone.domain.healthStat.entity.HealthStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface HealthStatRepository extends JpaRepository<HealthStat, Integer> {
    Optional<HealthStat> findByUserNoAndCreateTimeBetween(Integer no, LocalDateTime startDateTime, LocalDateTime endDateTime);
    Optional<HealthStat> findTopByUserNoOrderByCreateTimeDesc(Integer userNo);
    HealthStat save (HealthStat healthStat);
    void deleteByNoAndUserNo(Integer no, Integer userNo);
}
