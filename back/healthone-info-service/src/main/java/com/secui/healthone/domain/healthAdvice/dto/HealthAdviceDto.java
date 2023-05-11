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
    @Schema(description = "건강 조언 식별번호")
    private Integer no;
    @Schema(description = "건강 조언 유저 식별번호")
    private Integer userNo;
    @Schema(description = "기록한 시간")
    private LocalDateTime createtime;
    @Schema(description = "체중 조언")
    private String weight;
    @Schema(description = "bmi 조언")
    private String bmi;
    @Schema(description = "체지방률 조언")
    private String fatPercentage;
    @Schema(description = "공복 혈당 조언")
    private String fbg;
    @Schema(description = "골격근량 조언")
    private String skeletalMuscleMass;
    @Schema(description = "혈압 조언")
    private String bloodPressure;
    @Schema(description = "hdl 콜레스테롤 조언")
    private String hdlCholesterol;
    @Schema(description = "중성지방 조언")
    private String tg;
}
