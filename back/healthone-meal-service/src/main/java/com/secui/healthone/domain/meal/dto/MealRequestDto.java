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
public class MealRequestDto {
    private Integer no;
    private Integer foodNo;
    private Integer customfoodNo;
    private LocalDateTime createTime;
    private MealType mealType;
    private Float portion;
    private Float gram;
    private Float kcal;

    public Meal toEntity() {
        return Meal.builder()
                .no(no)
                .foodNo(foodNo)
                .customfoodNo(customfoodNo)
                .createTime(createTime)
                .mealType(mealType)
                .portion(portion)
                .gram(gram)
                .kcal(kcal)
                .build();
    }
}
