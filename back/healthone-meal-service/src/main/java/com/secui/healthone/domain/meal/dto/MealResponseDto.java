package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.entity.MealType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealResponseDto {
    private Integer no;
    private Integer foodNo;
    private Integer customfoodNo;
    private LocalDateTime createTime;
    private MealType mealType;
    private Float portion;
    private Float gram;
    private Integer kcal;

    @Builder
    public MealResponseDto(Meal entity) {
        this.no = entity.getNo();
        this.foodNo = entity.getFoodNo();
        this.customfoodNo = entity.getCustomfoodNo();
        this.createTime = entity.getCreateTime();
        this.mealType = entity.getMealType();
        this.portion = entity.getPortion();
        this.gram = entity.getGram();
        this.kcal = entity.getKcal();
    }
}
