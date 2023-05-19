package com.secui.healthone.domain.walk.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "Walk 요청(등록, 수정) DTO")
public class WalkReqDto {
    @Schema(description = "회원 식별 번호")
    private Integer userNo;
    @Schema(description = "걸음 수")
    @NotNull(message = "걸음 수 는 null 일 수 없습니다.")
    private Integer stepCount;
    @Schema(description = "이동 거리")
    @NotNull(message = "이동 거리 는 null 일 수 없습니다.")
    private Float distance;
}
