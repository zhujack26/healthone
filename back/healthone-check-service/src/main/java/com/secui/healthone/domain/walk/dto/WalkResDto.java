package com.secui.healthone.domain.walk.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "Walk 응답 DTO")
public class WalkResDto {
    @Schema(description = "Walk 식별번호")
    private Integer no;
    @Schema(description = "회원 식별 번호")
    private Integer userNo;
    @Schema(description = "기록한 시간")
    private LocalDateTime createtime;
    @Schema(description = "걸음 수")
    private Integer stepCount;
    @Schema(description = "이동 거리")
    private Float distance;
}
