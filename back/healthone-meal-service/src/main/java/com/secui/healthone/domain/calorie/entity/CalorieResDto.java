package com.secui.healthone.domain.calorie.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "칼로리 응답 DTO")
public class CalorieResDto {
    @Schema(name = "칼로리 데이터 식별번호")
    private Integer no;
    @Schema(name = "회원 식별 번호")
    private Integer userNo;
    @Schema(name = "칼로리 데이터 날짜")
    private LocalDateTime createTime;
    @Schema(name = "해당 날짜 총 소비 칼로리")
    private Integer sumKcalConsume;
    @Schema(name = "해당 날짜 총 섭취 칼로리")
    private Integer sumKcalEaten;
}
