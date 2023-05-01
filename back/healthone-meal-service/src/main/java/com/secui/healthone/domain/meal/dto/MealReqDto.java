package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.entity.Food;
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
public class MealReqDto {
    private Integer no;
    private Integer userNo;
    private Integer foodNo;
    private Integer customfoodNo;
    private LocalDateTime createTime;
    private MealType mealType;
    private Float portion;
    private Float gram;
    private Integer kcal;

    public Meal toEntity(Food food, CustomFood customFood) {
        return Meal.builder()
                .no(no)
                .userNo(userNo)
                .food(food)
                .customfood(customFood)
                .createTime(createTime)
                .mealType(mealType)
                .portion(portion)
                .gram(gram)
                .kcal(kcal)
                .build();
    }
}
