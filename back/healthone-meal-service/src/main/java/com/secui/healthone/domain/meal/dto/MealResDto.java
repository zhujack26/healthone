package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.entity.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "식사 응답 요청 DTO")
public class MealResDto {
    @Schema(description = "식사 식별번호")
    private Integer no;
    @Schema(description = "회원 식별번호")
    private Integer userNo;
    @Schema(description = "음식 이름")
    private String name;
    @Schema(description = "식사 기록 시간")
    private LocalDateTime createTime;
    @Schema(description = "식사 타입 (BREAKFAST, LUNCH, DINNER, SNACK)")
    private MealType mealType;
    @Schema(description = "섭취한 그램 수")
    private Float gram;
    @Schema(description = "섭취한 칼로리 수")
    private Integer kcal;

    @Builder
    public MealResDto(Meal entity) {
        this.no = entity.getNo();
        this.userNo = entity.getUserNo();
        this.name = entity.getName();
        this.createTime = entity.getCreateTime();
        this.mealType = entity.getMealType();
        this.gram = entity.getGram();
        this.kcal = entity.getKcal();
    }
}
