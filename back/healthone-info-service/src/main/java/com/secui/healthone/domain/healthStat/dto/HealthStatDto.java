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
    @Schema(description = "건강 기록 식별번호")
    private Integer no;
    @Schema(description = "유저 식별번호")
    private Integer userNo;
    @Schema(description = "건강 기록 날짜")
    @NotNull(message = "건강 기록 날짜는 null 일 수 없습니다.")
    private LocalDateTime createTime;
    @Schema(description = "건강기록 : 키")
    @NotNull(message = "키는 null 일 수 없습니다.")
    private Float height;
    @Schema(description = "건강기록 : 몸무게")
    @NotNull(message = "몸무게는 null 일 수 없습니다.")
    private Float weight;
    @Schema(description = "건강 기록 : BMI")
    @NotNull(message = "bmi는 null 일 수 없습니다.")
    private Float bmi;
    @Schema(description = "건강 기록 : 체지방률")
    @NotNull(message = "체지방률은 null 일 수 없습니다.")
    private Float bodyFatPercentage;
    @Schema(description = "건강 기록 : 골격근량")
    @NotNull(message = "골격근량은 null 일 수 없습니다.")
    private Float skeletalMuscleMass;
    @Schema(description = "건강 기록 : 허리둘레")
    @NotNull(message = "허리 둘레는 null 일 수 없습니다.")
    private Float waistMeasurement;
    @Schema(description = "건강 기록 : 최저 혈압")
    @NotNull(message = "수축기 혈압은 null 일 수 없습니다.")
    private Float lowBloodPressure;
    @Schema(description = "건강 기록 : 최고 혈압")
    @NotNull(message = "이완기 혈압은 null 일 수 없습니다.")
    private Float highBloodPressure;
    @Schema(description = "건강 기록 : 공복혈당")
    @NotNull(message = "공복혈당 null 일 수 없습니다.")
    private Float fbg;
    @Schema(description = "건강 기록 : HDL콜레스테롤")
    @NotNull(message = "HDL콜레스테롤은 null 일 수 없습니다.")
    private Float hdlCholesterol;
    @Schema(description = "건강 기록 : 중성지방")
    @NotNull(message = "중성지방은 null 일 수 없습니다.")
    private Float tg;
}
