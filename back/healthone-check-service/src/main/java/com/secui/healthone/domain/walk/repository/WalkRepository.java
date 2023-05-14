package com.secui.healthone.domain.walk.repository;

import com.secui.healthone.domain.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk, Integer> {

    List<Walk> findAllByUserNoAndCreatetimeBetween(Integer user, LocalDateTime createtime1, LocalDateTime createtime2);
    List<Walk> findAllByUserNoAndCreatetime(Integer userNo, LocalDateTime createtime);
    Optional<Walk> findByNoAndUserNo(Integer no, Integer userNo);
    Walk save(Walk walk);
    void deleteByNoAndUserNo(Integer no, Integer userNo);
    List<Walk> findAllByUserNoOrderByCreatetimeDesc(Integer userNo);
}
