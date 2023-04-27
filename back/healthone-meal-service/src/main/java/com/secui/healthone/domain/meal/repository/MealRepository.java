package com.secui.healthone.domain.meal.repository;

import com.secui.healthone.domain.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Optional<Meal> findByNoAndCalorieNo(Integer no, Integer calorieNo);
    void deleteAllByNoAndCalorieNo(Integer no, Integer calorieNo);
}
