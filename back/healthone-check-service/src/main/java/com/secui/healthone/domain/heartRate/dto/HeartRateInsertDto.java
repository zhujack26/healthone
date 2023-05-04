package com.secui.healthone.domain.heartRate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "심박수 등록 DTO")
public class HeartRateInsertDto {
    @Schema(description = "유저 식별 번호")
    private Integer userNo;
    @Schema(description = "심박수 등록 시간")
    private LocalDateTime createTime;
    @Schema(description = "심박수 카운트")
    private Integer count;
}
