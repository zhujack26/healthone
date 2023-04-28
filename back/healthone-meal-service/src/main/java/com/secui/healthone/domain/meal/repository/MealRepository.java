package com.secui.healthone.domain.meal.repository;

import com.secui.healthone.domain.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Optional<Meal> findMealByNo(Integer no);

    // 해당 날짜 식단 정보 가져오기
//    List<Meal> findAllByCreateTime(LocalDate createTime);
    List<Meal> findByCreateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    void deleteAllByNo(Integer no);
}
