package com.secui.healthone.domain.sportrecord.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class SportRecordReqDto {
    private Integer userNo;
    private Integer sportNo;
    private Integer customSportNo;
    private LocalDateTime createTime;
    private Integer workTime;
    private Integer consumeCalorie;
    private Integer heartRate;
    private Integer bloodPressure;
}
