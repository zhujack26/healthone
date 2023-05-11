package com.secui.healthone.domain.sportrecord.dto;

import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import com.secui.healthone.domain.sport.dto.SportResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "운동 기록 응답 DTO")
public class SportRecordResDto {
    @Schema(description = "운동 기록 식별번호")
    private Integer no;
    @Schema(description = "유저 식별 번호")
    private Integer userNo;
    @Schema(description = "운둥 이름")
    private String name;
    @Schema(description = "운둥 기록 시간(날짜)")
    private LocalDateTime createTime;
    @Schema(description = "운동 시간")
    private Integer workTime;
    @Schema(description = "소모된 칼로리 양")
    private Integer consumeCalorie;
    @Schema(description = "평균 심박수")
    private Integer heartRate;
    @Schema(description = "평균 혈압")
    private Integer bloodPressure;
}
