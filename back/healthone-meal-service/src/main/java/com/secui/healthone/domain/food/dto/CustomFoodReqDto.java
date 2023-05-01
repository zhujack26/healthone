package com.secui.healthone.domain.food.dto;

import com.secui.healthone.domain.food.entity.CustomFood;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 음식 등록, 수정 요청 DTO")
public class CustomFoodReqDto {
    @Schema(description = "사용자 음식 식별번호")
    private Integer no;
    @Schema(description = "회원 식별번호")
    private Integer userNo;
    @Schema(description = "사용자 음식 이름")
    private String name;
    @Schema(description = "사용자 음식 1인분당 칼로리")
    private Integer kcal;
    @Schema(description = "사용자 음식 1인분당 그램 수")
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
