package com.secui.healthone.domain.healthAdvice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "건강 조언 DTO")
public class HealthAdviceDto {
    @Schema(description = "건강 조언 유저 식별번호")
    private Integer userNo;
    @Schema(description = "기록한 시간")
    private LocalDateTime createtime;
//    @Schema(description = "신장 조언")
//    private String height;
    @Schema(description = "체중 조언")
    private String weight;
    @Schema(description = "BMI 조언")
    private AdviceType bmi;
    @Schema(description = "체지방률 조언")
    private AdviceType fatPercentage;
    @Schema(description = "허리둘레 조언")
    private AdviceType waistMeasurement;
    @Schema(description = "공복혈당 조언")
    private AdviceType fbg;
    @Schema(description = "골격근량 조언")
    private AdviceType skeletalMuscleMass;
    @Schema(description = "혈압 조언")
    private AdviceType bloodPressure;
    @Schema(description = "콜레스테롤 조언")
    private AdviceType hdlCholesterol;
    @Schema(description = "중성지방 조언")
    private AdviceType tg;
}
