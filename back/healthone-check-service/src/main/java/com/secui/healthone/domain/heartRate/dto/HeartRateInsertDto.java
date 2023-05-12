package com.secui.healthone.domain.heartRate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "심박수 등록 시간 은 null 일 수 없습니다.")
    private LocalDateTime createTime;
    @Schema(description = "심박수 카운트")
    @NotNull(message = "심박수 카운트 는 null 일 수 없습니다.")
    private Integer count;
}
