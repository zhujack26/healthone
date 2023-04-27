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

    public MealResponseDto getMeal(Integer no) {
        Optional<Meal> result = mealRepository.findByNoAndCalorieNo(no, 1);
        return result.map(MealResponseDto::new).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
    }

    @Transactional
    public MealResponseDto insertMeal(MealRequestDto requestDto) {
        return null;
    }



}
