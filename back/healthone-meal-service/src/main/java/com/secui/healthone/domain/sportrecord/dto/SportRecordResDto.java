package com.secui.healthone.domain.sportrecord.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class SportRecordResDto {
    private Integer no;
    private Integer userNo;
    private Integer sportNo;
    private Integer customSportNo;
    private Integer workTime;
    private Integer consumeCalorie;
    private Integer heartRate;
    private Integer bloodPressure;
}
