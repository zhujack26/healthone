package com.secui.healthone.domain.healthStat.repository;

import com.secui.healthone.domain.healthStat.entity.HealthStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthStatRepository extends JpaRepository<HealthStat, Integer> {
    HealthStat findByUserNoAndCreatetimeBetween(int i, LocalDateTime startDateTime, LocalDateTime endDateTime);

    void deleteByNoAndUserNo(Integer no, Integer userNo);

}
