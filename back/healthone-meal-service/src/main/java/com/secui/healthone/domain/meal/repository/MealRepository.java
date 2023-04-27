package com.secui.healthone.domain.meal.repository;

import com.secui.healthone.domain.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer> {
//    @Query("select m from Meal m where m.no =:no and m.calorieNo =: calorieNo")
    Optional<Meal> findMealByNoAndCalorieNo(Integer no, Integer calorieNo);
    void deleteAllByNoAndCalorieNo(Integer no, Integer calorieNo);
}
