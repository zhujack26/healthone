package com.secui.healthone.domain.meal.service;

import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.entity.Food;
import com.secui.healthone.domain.food.repository.CustomFoodRepository;
import com.secui.healthone.domain.food.repository.FoodRepository;
import com.secui.healthone.domain.meal.dto.MealReqDto;
import com.secui.healthone.domain.meal.dto.MealResDto;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.repository.MealRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService {

    private final MealRepository mealRepository;

    // 식사 조회
    public MealResDto getMeal(Integer no, Integer userNo) {
        Optional<Meal> result = mealRepository.findMealByNoAndUserNo(no, userNo);
        return result.map(MealResDto::new).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
    }

    // 식사 일자별 검색
    public List<MealResDto> getMealList(String date, Integer userNo) {
        StringDateTrans trans = new StringDateTrans(date);
        List<Meal> result = mealRepository.findByCreateTimeBetweenAndUserNo(trans.getStartDateTime(), trans.getEndDateTime(), userNo);
        return result.stream().map(MealResDto::new).collect(Collectors.toList());
    }

    // 식사 등록
    @Transactional
    public MealResDto insertMeal(MealReqDto requestDto) {
        Meal result = mealRepository.save(requestDto.toEntity());
        return new MealResDto(result);
    }

    // 식사 수정
    @Transactional
    public MealResDto modifyMeal(MealReqDto requestDto) {
        Meal meal = mealRepository.findMealByNoAndUserNo(requestDto.getNo(), requestDto.getUserNo())
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        meal.update(requestDto.toEntity());
        return new MealResDto(meal);
    }

    // 식사 삭제
    @Transactional
    public void deleteMeal(Integer no, Integer userNo) {
        Meal meal = mealRepository.findMealByNoAndUserNo(no, userNo)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        mealRepository.deleteAllByNoAndUserNo(no, userNo);
    }

}
