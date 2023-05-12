package com.secui.healthone.domain.meal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MealType {
    BREAKFAST("BREAKFAST" , "식사타입: 아침"),
    LUNCH("LUNCH", "식사타입: 점심"),
    DINNER("DINNER", "식사타입: 저녁"),
    SNACK("SNACK", "식사타입: 간식");

    private final String name;
    private final String description;

}
