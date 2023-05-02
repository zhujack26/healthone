package com.secui.healthone.domain.calorie.service;

import com.secui.healthone.domain.calorie.dto.CalorieResponseDto;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.repository.MealRepository;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import com.secui.healthone.domain.sportrecord.repository.SportRecordRepository;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalorieService {
    private final SportRecordRepository sportRecordRepository;
    private final MealRepository mealRepository;
//    private final CalorieRepository calorieRepository;
//    private final CalorieDtoMapper calorieDtoMapper;

    public CalorieResponseDto getCalorie(String date, Integer userNo) {
        StringDateTrans transDate = new StringDateTrans(date);

        List<Meal> getMeal = mealRepository.findByCreateTimeBetweenAndUserNo(transDate.getStartDateTime(), transDate.getEndDateTime(), 1);
        Integer totalEatenKcal = 0;
        for (Meal meal : getMeal) {
            totalEatenKcal += meal.getKcal();
        }
        List<SportRecord> getSportRecord = sportRecordRepository.findByCreateTimeBetweenAndUserNo(transDate.getStartDateTime(), transDate.getEndDateTime(), 1);
        Integer totalConsumeKcal = 0;
        for (SportRecord sportRecord : getSportRecord) {
            totalConsumeKcal += sportRecord.getConsumeCalorie();
        }

        return CalorieResponseDto.builder()
                .userNo(userNo)
                .sumKcalEaten(totalEatenKcal)
                .sumKcalConsume(totalConsumeKcal)
                .createTime(transDate.getStartDateTime())
                .build();
    }
}
