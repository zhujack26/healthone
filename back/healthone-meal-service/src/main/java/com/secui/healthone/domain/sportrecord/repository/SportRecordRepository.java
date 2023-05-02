package com.secui.healthone.domain.sportrecord.repository;

import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SportRecordRepository extends JpaRepository<SportRecord, Integer> {
    List<SportRecord> findByCreateTimeBetweenAndUserNo(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer userNo);
}
