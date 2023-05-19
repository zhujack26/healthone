package com.secui.healthone.domain.food.repository;

import com.secui.healthone.domain.food.entity.Food;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Slice<Food> findAllByNameContaining(String name, Pageable pageable);
}
