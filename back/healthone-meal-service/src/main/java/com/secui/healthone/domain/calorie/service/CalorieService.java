package com.secui.healthone.domain.calorie.service;

import com.secui.healthone.domain.calorie.dto.CalorieResDto;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.repository.MealRepository;
import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import com.secui.healthone.domain.sportrecord.repository.SportRecordRepository;
import com.secui.healthone.global.error.errorcode.CommonErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.util.StringDateTrans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.secui.healthone.global.util.StringDateTrans.stringDateTrans7Days;

@Service
@RequiredArgsConstructor
public class CalorieService {
    private final SportRecordRepository sportRecordRepository;
    private final MealRepository mealRepository;
    public CalorieResDto getCalorie(String date, Integer userNo, String mode) {
        StringDateTrans transDate = null;
        if(mode.equals("day")) {
            transDate = new StringDateTrans(date);
        } else if (mode.equals("week")) {
            transDate = stringDateTrans7Days(date);
        } else {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        List<Meal> getMeal = mealRepository.findByCreateTimeBetweenAndUserNo(transDate.getStartDateTime(), transDate.getEndDateTime(), userNo);
        Integer totalEatenKcal = 0;
        for (Meal meal : getMeal) {
            totalEatenKcal += meal.getKcal();
        }
        List<SportRecord> getSportRecord = sportRecordRepository.findByCreateTimeBetweenAndUserNo(transDate.getStartDateTime(), transDate.getEndDateTime(), userNo);
        Integer totalConsumeKcal = 0;
        for (SportRecord sportRecord : getSportRecord) {
            totalConsumeKcal += sportRecord.getConsumeCalorie();
        }
        return CalorieResDto.builder()
                .userNo(userNo)
                .sumKcalEaten(totalEatenKcal)
                .sumKcalConsume(totalConsumeKcal)
                .createTime(transDate.getStartDateTime())
                .build();
    }
}
