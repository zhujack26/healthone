package com.secui.healthone.domain.healthStat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "건강 기록 DTO")
public class HealthStatDto {
    private Integer no;
    private Integer userNo;
    private String createtime;
    private Float height;
    private Float weight;
    private Float bmi;
    private Float bodyFatPercentage;
    private Float skeletalMuscleMass;
    private Float waistMeasurement;
    private Float bloodPressure;
    private Float fbg;
    private Float hdlCholesterol;
    private Float tg;
}
