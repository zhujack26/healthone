package com.secui.healthone.domain.sleep.dto;

import com.secui.healthone.domain.sleep.entity.Sleep;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "수면 정보 반환 DTO")
public class SleepResDto {
    @Schema(description = "수면 데이터 식별 번호")
    private Integer no;
    @Schema(description = "회원 식별 번호")
    private Integer userNo;
    @Schema(description = "수면 날짜")
    private String createTime;
    @Schema(description = "수면 시작 시간")
    private String startSleepTime;
    @Schema(description = "수면 기상 시간")
    private String endSleepTime;

    @Builder
    public SleepResDto (Sleep entity) {
        this.no = entity.getNo();
        this.userNo = entity.getUserNo();
        this.createTime = String.valueOf(entity.getCreatetime());
        this.startSleepTime = String.valueOf(entity.getStartSleepTime());
        this.endSleepTime = String.valueOf(entity.getEndSleepTime());
    }
}
