package com.secui.healthone.domain.food.dto;

import com.secui.healthone.domain.food.entity.Food;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "일반 음식 응답 DTO")
public class FoodResponseDto {
    @Schema(description = "음식 식별번호")
    private Integer no;
    @Schema(description = "음식 이름")
    private String name;
    @Schema(description = "음식 1인분당 칼로리")
    private Integer kcal;
    @Schema(description = "음식 1인분당 그램 수")
    private Float gram;

    @Builder
    public FoodResponseDto(Food entity) {
        this.no = entity.getNo();
        this.name = entity.getName();
        this.kcal = entity.getKcal();
        this.gram = entity.getGram();
    }
}
