package com.secui.healthone.domain.heartRate.dto;

import com.secui.healthone.domain.heartRate.entity.HeartRate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Schema(description = "심박수 응답 DTO")
public class HeartRateResDto {
    @Schema(description = "심박수 식별번호")
    private Integer no;
    @Schema(description = "회원 식별 번호")
    private Integer userNo;
    @Schema(description = "기록한 시간")
    private LocalDateTime createTime;
    @Schema(description = "심박수")
    private Integer count;

    @Builder
    public HeartRateResDto(HeartRate entity) {
        this.no = entity.getNo();
        this.userNo = entity.getUserNo();
        this.createTime = entity.getCreateTime();
        this.count = entity.getCount();
    }
}
