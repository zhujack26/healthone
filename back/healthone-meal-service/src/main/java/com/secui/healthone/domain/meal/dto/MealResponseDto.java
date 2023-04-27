package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.meal.entity.Meal;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealResponseDto {
    private Integer no;
    private String calorieNo;
    private Integer foodNo;
    private Integer customfoodNo;
    private Float portion;
    private Float gram;
    private Float kcal;

    @Builder
    public MealResponseDto(Meal entity) {
        this.no = entity.getNo();
        this.calorieNo = entity.getCalorieNo();
        this.foodNo = entity.getFoodNo();
        this.customfoodNo = entity.getCustomfoodNo();
        this.portion = entity.getPortion();
        this.gram = entity.getGram();
        this.kcal = entity.getKcal();
    }
}
