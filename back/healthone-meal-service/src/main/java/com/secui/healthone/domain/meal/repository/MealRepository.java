package com.secui.healthone.domain.meal.repository;

import com.secui.healthone.domain.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Optional<Meal> findMealByNoAndUserNo(Integer no, Integer userNo);
    // 해당 날짜 식단 정보 가져오기
//    List<Meal> findAllByCreateTime(LocalDate createTime);
    List<Meal> findByCreateTimeBetweenAndUserNo(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer userNo);
    List<Meal> findAllByUserNo(Integer userNo);
    Meal save(Meal meal);
    void deleteAllByNoAndUserNo(Integer no, Integer userNo);
}
