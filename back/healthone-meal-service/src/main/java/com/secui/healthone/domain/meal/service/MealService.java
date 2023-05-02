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
    private final FoodRepository foodRepository;
    private final CustomFoodRepository customFoodRepository;

    // 식사 조회
    public MealResDto getMeal(Integer no) {
        Optional<Meal> result = mealRepository.findMealByNo(no);
        return result.map(MealResDto::new).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
    }

    // 식사 일자별 검색
    public List<MealResDto> getMealList(String date, Integer userNo) throws ParseException {
        log.info("date : {}", date);
        StringDateTrans trans = new StringDateTrans(date);
        List<Meal> result = mealRepository.findByCreateTimeBetweenAndUserNo(trans.getStartDateTime(), trans.getEndDateTime(), userNo);
        return result.stream().map(MealResDto::new).collect(Collectors.toList());
    }

    // 식사 등록, 수정
    @Transactional
    public MealResDto insertMeal(MealReqDto requestDto) {
        Food food = null;
        CustomFood customFood = null;
        if (requestDto.getFoodNo() != null){
            food= foodRepository.findById(requestDto.getFoodNo())
                    .orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        } else if (requestDto.getCustomfoodNo()!= null){
            customFood = customFoodRepository.findAllByNoAndUserNo(requestDto.getCustomfoodNo(), requestDto.getUserNo())
                    .orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        }
        mealRepository.save(requestDto.toEntity(food,customFood));
        return new MealResDto(requestDto.toEntity(food,customFood));
    }

    // 식사 삭제
    @Transactional
    public void deleteMeal(Integer no) {
        Meal meal = mealRepository.findMealByNo(no)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        mealRepository.delete(meal);
    }

}
