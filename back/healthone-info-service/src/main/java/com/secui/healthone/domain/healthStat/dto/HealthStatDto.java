package com.secui.healthone.domain.healthStat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "건강 기록 DTO")
public class HealthStatDto {
    private Integer no;
    private Integer userNo;
    @NotNull(message = "건강 기록 날짜는 null 일 수 없습니다.")
    private LocalDateTime createTime;
    @NotNull(message = "키는 null 일 수 없습니다.")
    private Float height;
    @NotNull(message = "몸무게는 null 일 수 없습니다.")
    private Float weight;
    @NotNull(message = "bmi는 null 일 수 없습니다.")
    private Float bmi;
    @NotNull(message = "체지방률은 null 일 수 없습니다.")
    private Float bodyFatPercentage;
    @NotNull(message = "골격근량은 null 일 수 없습니다.")
    private Float skeletalMuscleMass;
    @NotNull(message = "허리 둘레는 null 일 수 없습니다.")
    private Float waistMeasurement;
    @NotNull(message = "혈압 null 일 수 없습니다.")
    private Float bloodPressure;
    @NotNull(message = "공복혈당 null 일 수 없습니다.")
    private Float fbg;
    @NotNull(message = "HDL콜레스테롤은 null 일 수 없습니다.")
    private Float hdlCholesterol;
    @NotNull(message = "중성지방은 null 일 수 없습니다.")
    private Float tg;
}
