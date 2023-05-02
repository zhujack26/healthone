package com.secui.healthone.domain.calorie.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class CalorieResponseDto {
//    private Integer no;
    private Integer userNo;
    private LocalDateTime createTime;
    private Integer sumKcalConsume;
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
