package com.secui.healthone.domain.healthStat.repository;

import com.secui.healthone.domain.healthStat.entity.HealthStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface HealthStatRepository extends JpaRepository<HealthStat, Integer> {
    HealthStat findByUserNoAndCreatetimeBetween(Integer no, LocalDateTime startDateTime, LocalDateTime endDateTime);
    HealthStat save (HealthStat healthStat);
    void deleteByNoAndUserNo(Integer no, Integer userNo);
}
