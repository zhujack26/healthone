package com.secui.healthone.domain.meal.service;

import com.secui.healthone.domain.meal.dto.MealRequestDto;
import com.secui.healthone.domain.meal.dto.MealResponseDto;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.repository.MealRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    // 식사 조회
    public MealResponseDto getMeal(Integer no, Integer calorieNo) {
        Optional<Meal> result = mealRepository.findMealByNoAndCalorieNo(no, calorieNo);
        return result.map(MealResponseDto::new).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
    }

    // 식사 등록
    @Transactional
    public MealResponseDto insertMeal(MealRequestDto requestDto) {
        mealRepository.save(requestDto.toEntity());
        return new MealResponseDto(requestDto.toEntity());
    }

    // 식사 수정
    @Transactional
    public MealResponseDto updateMeal(MealRequestDto requestDto) {
        Meal meal = mealRepository.findMealByNoAndCalorieNo(requestDto.getNo(), requestDto.getCalorieNo())
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        meal.update(requestDto.toEntity());
        return new MealResponseDto(meal);
    }

    // 식사 삭제
    @Transactional
    public void deleteMeal(Integer no) {
        Meal meal = mealRepository.findMealByNoAndCalorieNo(no, 1)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        mealRepository.delete(meal);
    }
    
}
