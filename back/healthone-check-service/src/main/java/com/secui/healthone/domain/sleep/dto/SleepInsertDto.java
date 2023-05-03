package com.secui.healthone.domain.sleep.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "수면 등록 DTO")
public class SleepInsertDto {
    @Schema(description = "회원 식별 번호")
    private int userNo;
    @Schema(description = "수면 날짜")
    private String createTime;
    @Schema(description = "수면 시작 시간")
    private String startSleepTime;
    @Schema(description = "수면 기상 시간")
    private String endSleepTime;

}
