package com.secui.healthone.domain.sportrecord.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "운동 기록 요청(등록, 수정) DTO")
public class SportRecordReqDto {
    @Schema(description = "운동 기록 식별번호")
    private Integer no;
    @Schema(description = "유저 식별 번호")
    private Integer userNo;
    @Schema(description = "일반 운동 데이터 식별번호")
    private Integer sportNo;
    @Schema(description = "사용자 운동 데이터 식별번호")
    private Integer customSportNo;
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
