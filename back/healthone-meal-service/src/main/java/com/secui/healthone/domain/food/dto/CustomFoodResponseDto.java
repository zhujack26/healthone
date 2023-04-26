package com.secui.healthone.domain.food.dto;

import com.secui.healthone.domain.food.entity.CustomFood;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomFoodResponseDto {

    private Integer no;
    private Integer userNo;
    private String name;
    private Integer kcal;
    private Float gram;

    @Builder
    public CustomFoodResponseDto(CustomFood entity) {
        this.no = entity.getNo();
        this.userNo = entity.getUserNo();
        this.name = entity.getName();
        this.kcal = entity.getKcal();
        this.gram = entity.getGram();
    }
}
