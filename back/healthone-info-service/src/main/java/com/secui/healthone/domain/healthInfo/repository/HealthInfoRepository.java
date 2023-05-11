package com.secui.healthone.domain.healthInfo.repository;

import com.secui.healthone.domain.healthInfo.entity.HealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthInfoRepository extends JpaRepository<HealthInfo, Integer> {
//    Optional<HealthInfo> findByUserNo(int userNo);
    Optional<HealthInfo> findByNoAndUserNo(Integer no, Integer userNo);
}
