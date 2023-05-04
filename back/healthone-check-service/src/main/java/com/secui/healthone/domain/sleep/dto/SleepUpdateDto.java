package com.secui.healthone.domain.sleep.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "수면 정보 수정 요청 DTO")
public class SleepUpdateDto {
    @Schema(description = "수면 데이터 식별 번호")
    private String no;
    @Schema(description = "수면 데이터 식별 번호")
    private String createTime;
    @Schema(description = "수면 시작 시간")
    private String startSleepTime;
    @Schema(description = "수면 기상 시간")
    private String endSleepTime;

}
