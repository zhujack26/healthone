package com.secui.healthone.domain.food.repository;

import com.secui.healthone.domain.food.entity.CustomFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomFoodRepository extends JpaRepository<CustomFood, Integer> {
    Optional<CustomFood> findAllByNoAndUserNo(Integer no, Integer userNo);

}
