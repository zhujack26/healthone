package com.secui.healthone.domain.heartRate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "HeartRate 요청(등록, 수정) DTO")
public class HeartRateReqDto {
    @Schema(description = "HeartRate 식별번호")
    private Integer no;
    @Schema(description = "회원 식별 번호")
    private Integer userNo;
    @Schema(description = "기록한 시간")
    private LocalDateTime createTime;
    @Schema(description = "심박수")
    private Integer count;
}
