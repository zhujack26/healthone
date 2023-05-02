package com.secui.healthone.domain.sportrecord.dto;

import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import com.secui.healthone.domain.sport.dto.SportResDto;
import com.secui.healthone.domain.sport.entity.CustomSport;
import com.secui.healthone.domain.sport.entity.Sport;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class SportRecordResDto {
    private Integer no;
    private Integer userNo;
    private SportResDto sportResDto;
    private CustomSportResDto customSportResDto;
    private LocalDateTime createTime;
    private Integer workTime;
    private Integer consumeCalorie;
    private Integer heartRate;
    private Integer bloodPressure;
}
