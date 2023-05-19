package com.secui.healthone.domain.sport.repository;

import com.secui.healthone.domain.sport.entity.CustomSport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomSportRepository extends JpaRepository<CustomSport, Integer> {

    List<CustomSport> findByUserNoAndNameContaining(Integer userNo, String name);
    Optional<CustomSport> findByNoAndUserNo(Integer userNo, Integer no);
    CustomSport save(CustomSport customSport);
    void deleteByNoAndUserNo(Integer no, Integer userNo);
}
