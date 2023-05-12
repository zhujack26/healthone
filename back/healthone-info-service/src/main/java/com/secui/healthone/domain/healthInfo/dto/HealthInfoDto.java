package com.secui.healthone.domain.healthInfo.dto;

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
@Schema(description = "회원 헬스정보 DTO")
public class HealthInfoDto {
    @Schema(description = "회원 헬스 정보 식별번호")
    private Integer no;
    @Schema(description = "회원 식별번호")
    private Integer userNo;
    @Schema(description = "성별")
    private Boolean gender;
    @Schema(description = "생년월일")
    private LocalDateTime birthdate;
    @Schema(description = "키")
    private Float height;
    @Schema(description = "몸무게")
    private Float weight;
    @Schema(description = "활동량")
    private String workRate;
    @Schema(description = "목표 걸음 수")
    private Integer stepGoal;
    @Schema(description = "목표 취침 시간")
    private LocalDateTime sleepTime;
    @Schema(description = "목표 기상 시간")
    private LocalDateTime wakeUpTime;
    @Schema(description = "목표 수면 시간")
    private Integer sleepGoal;
}
