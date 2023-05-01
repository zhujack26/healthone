package com.secui.healthone.domain.food.dto;

import com.secui.healthone.domain.food.entity.CustomFood;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomFoodReqDto {
    private Integer no;
    private Integer userNo;
    private String name;
    private Integer kcal;
    private Float gram;

    public CustomFood toEntity() {
        return CustomFood.builder()
                .no(no)
                .userNo(userNo)
                .name(name)
                .kcal(kcal)
                .gram(gram)
                .build();
    }
}
