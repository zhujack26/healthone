package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.food.dto.CustomFoodResDto;
import com.secui.healthone.domain.food.dto.FoodResponseDto;
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
public class MealResDto {
    private Integer no;
    private FoodResponseDto food;
    private CustomFoodResDto customfood;
    private LocalDateTime createTime;
    private MealType mealType;
    private Float portion;
    private Float gram;
    private Integer kcal;

    @Builder
    public MealResDto(Meal entity) {
        this.no = entity.getNo();
        this.food = entity.getFood() != null ? new FoodResponseDto(entity.getFood()) : null;
        this.customfood = entity.getCustomfood() != null ? new CustomFoodResDto(entity.getCustomfood()) : null;
        this.createTime = entity.getCreateTime();
        this.mealType = entity.getMealType();
        this.portion = entity.getPortion();
        this.gram = entity.getGram();
        this.kcal = entity.getKcal();
    }
}
