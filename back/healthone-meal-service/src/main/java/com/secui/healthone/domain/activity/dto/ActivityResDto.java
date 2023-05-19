package com.secui.healthone.domain.activity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "활동 응답 DTO")
public class ActivityResDto {
    @Schema(description = "총 활동 시간")
    private Integer totalActivityTime;
    @Schema(description = "일별 기준 가장 많은 활동한 시간")
    private Integer bestActivityTime;
    @Schema(description = "총 소비 칼로리")
    private Integer totalConsumeCalorie;
    @Schema(description = "일별 기준 가장 많은 활동한 시간")
    private Integer bestConsumeCalorie;
}
