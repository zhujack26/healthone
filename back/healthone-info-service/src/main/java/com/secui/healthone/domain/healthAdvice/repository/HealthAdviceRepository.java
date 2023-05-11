//package com.secui.healthone.domain.healthAdvice.repository;
//
//import com.secui.healthone.domain.healthAdvice.entity.HealthAdvice;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//public interface HealthAdviceRepository extends JpaRepository<HealthAdvice, Integer> {
//    Optional<HealthAdvice> findByCreatetimeAndUserNo(LocalDateTime localDateTime, int userNo);
//}
