package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.food.entity.CustomFood;
import com.secui.healthone.domain.food.entity.Food;
import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.entity.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "식사 등록, 수정 요청 DTO")
public class MealReqDto {
    @Schema(description = "식사 식별번호")
    private Integer no;
    @Schema(description = "회원 식별번호")
    private Integer userNo;
    @Schema(description = "일반 음식 식별번호")
    private Integer foodNo;
    @Schema(description = "사용자 음식 식별번호")
    private Integer customfoodNo;
    @Schema(description = "식사 기록 시간")
    private LocalDateTime createTime;
    @Schema(description = "식사 타입 (BREAKFAST, LUNCH, DINNER, SNACK)")
    private MealType mealType;
    @Schema(description = "섭취한 인분 수")
    private Float portion;
    @Schema(description = "섭취한 그램 수")
    private Float gram;
    @Schema(description = "섭취한 칼로리 수")
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
