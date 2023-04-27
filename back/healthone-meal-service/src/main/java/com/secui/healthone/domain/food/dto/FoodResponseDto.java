package com.secui.healthone.domain.food.dto;

import com.secui.healthone.domain.food.entity.Food;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FoodResponseDto {

    private Integer no;
    private String name;
    private Integer kcal;
    private Float gram;

    @Builder
    public FoodResponseDto(Food entity) {
        this.no = entity.getNo();
        this.name = entity.getName();
        this.kcal = entity.getKcal();
        this.gram = entity.getGram();
    }
}
