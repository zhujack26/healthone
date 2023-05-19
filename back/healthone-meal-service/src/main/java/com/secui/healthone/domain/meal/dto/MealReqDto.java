package com.secui.healthone.domain.meal.dto;

import com.secui.healthone.domain.meal.entity.Meal;
import com.secui.healthone.domain.meal.entity.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "식사 등록, 수정 요청 DTO")
public class MealReqDto {
    @Schema(description = "식사 식별번호")
    private Integer no;
    @Schema(description = "회원 식별번호")
    private Integer userNo;
    @Schema(description = "음식 이름")
    @NotNull(message = "음식 정보는 null 일 수 없습니다.")
    private String name;
    @Schema(description = "식사 기록 시간")
    @NotNull(message = "식사 기록 시간은 null 일 수 없습니다.")
    private LocalDateTime createTime;
    @Schema(description = "식사 타입 (BREAKFAST, LUNCH, DINNER, SNACK)")
    @NotNull(message = "식사 타입은 null 일 수 없습니다.")
    private MealType mealType;
    @Schema(description = "섭취한 그램 수")
    @NotNull(message = "섭취한 그램 수 는 null 일 수 없습니다.")
    private Float gram;
    @Schema(description = "섭취한 칼로리 수")
    @NotNull(message = "섭취한 칼로리 수 는 null 일 수 없습니다.")
    private Integer kcal;

    public Meal toEntity() {
        return Meal.builder()
                .no(no)
                .userNo(userNo)
                .name(name)
                .createTime(createTime)
                .mealType(mealType)
                .gram(gram)
                .kcal(kcal)
                .build();
    }
}
