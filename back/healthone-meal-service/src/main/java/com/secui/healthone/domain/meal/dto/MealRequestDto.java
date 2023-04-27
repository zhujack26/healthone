package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.meal.entity.Meal;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealRequestDto {
    private Integer no;
    private Integer calorieNo;
    private Integer foodNo;
    private Integer customfoodNo;
    private Float portion;
    private Float gram;
    private Float kcal;

    public Meal toEntity() {
        return Meal.builder()
                .no(no)
                .calorieNo(calorieNo)
                .foodNo(foodNo)
                .customfoodNo(customfoodNo)
                .portion(portion)
                .gram(gram)
                .kcal(kcal)
                .build();
    }
}
