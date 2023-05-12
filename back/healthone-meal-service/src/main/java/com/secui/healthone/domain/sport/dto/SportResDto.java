package com.secui.healthone.domain.sport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "일반 운동 데이터 응답 DTO")
public class SportResDto {
    @Schema(description = "일반 운동 데이터 식별번호")
    private Integer no;
    @Schema(description = "일반 운동 이름")
    private String name;
    @Schema(description = "일반 운동 시간당 소비 칼로리")
    private Integer consumeKcal;
}
