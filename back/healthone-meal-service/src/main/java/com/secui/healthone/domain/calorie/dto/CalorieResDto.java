package com.secui.healthone.domain.calorie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "칼로리 응답 DTO")
public class CalorieResDto {
//    private Integer no;
    @Schema(description = "회원 식별 번호")
    private Integer userNo;
    @Schema(description = "칼로리 데이터 날짜")
    private LocalDateTime createTime;
    @Schema(description = "해당 날짜 총 소비 칼로리")
    private Integer sumKcalConsume;
    @Schema(description = "해당 날짜 총 섭취 칼로리")
    private Integer sumKcalEaten;

//    @Builder
//    public CalorieResponseDto (Calorie entity,  Integer sumKcalConsume, Integer sumKcalEaten) {
//        this.no = entity.getNo();
//        this.userNo = entity.getUserNo();
//        this.createTime = entity.getCreateTime();
//        this.sumKcalConsume = sumKcalConsume;
//        this.sumKcalEaten = sumKcalEaten;
//    }
}
