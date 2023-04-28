package com.secui.healthone.domain.calorie.dto;

import com.secui.healthone.domain.calorie.entity.Calorie;
import com.secui.healthone.domain.calorie.entity.MealType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CalorieResponseDto {
    private Integer no;
    private Integer userNo;
    private MealType mealType;
    private LocalDateTime createTime;
    private Integer sumKcalConsume;
    private Integer sumKcalEaten;

    @Builder
    public CalorieResponseDto (Calorie entity,  Integer sumKcalConsume, Integer sumKcalEaten) {
        this.no = entity.getNo();
        this.userNo = entity.getUserNo();
        this.mealType = entity.getMealType();
        this.createTime = entity.getCreateTime();
        this.sumKcalConsume = sumKcalConsume;
        this.sumKcalEaten = sumKcalEaten;
    }
}
