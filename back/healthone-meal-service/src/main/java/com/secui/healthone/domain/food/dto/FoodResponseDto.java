package com.secui.healthone.domain.food.dto;

import com.secui.healthone.domain.food.entity.Food;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "일반 음식 응답 DTO")
public class FoodResponseDto {
    @Schema(description = "음식 식별번호")
    @NotNull(message = "음식 식별번호는 null 일 수 없습니다.")
    private Integer no;
    @Schema(description = "음식 이름")
    @NotNull(message = "음식 이름은 null 일 수 없습니다.")
    private String name;
    @Schema(description = "음식 1인분당 칼로리")
    @NotNull(message = "음식 1인분당 칼로리 null 일 수 없습니다.")
    private Integer kcal;
    @Schema(description = "음식 1인분당 그램 수")
    @NotNull(message = "음식 1인분당 그램 수는 null 일 수 없습니다.")
    private Float gram;

    @Builder
    public FoodResponseDto(Food entity) {
        this.no = entity.getNo();
        this.name = entity.getName();
        this.kcal = entity.getKcal();
        this.gram = entity.getGram();
    }
}
