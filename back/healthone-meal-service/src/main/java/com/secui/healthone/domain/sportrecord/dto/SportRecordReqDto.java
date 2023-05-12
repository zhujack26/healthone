package com.secui.healthone.domain.sportrecord.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
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
    @Schema(description = "운둥 이름")
    @NotNull(message = "운동 이름은 null 일 수 없습니다.")
    private String name;
    @Schema(description = "운둥 기록 시간(날짜)")
    @NotNull(message = "운동기록 시간은 null 일 수 없습니다.")
    private LocalDateTime createTime;
    @Schema(description = "운동 시간")
    @NotNull(message = "운동 시간은 null 일 수 없습니다.")
    private Float workTime;
    @Schema(description = "소모된 칼로리 양")
    @NotNull(message = "소모된 칼로리 양은 null 일 수 없습니다.")
    private Integer consumeCalorie;
    @Schema(description = "평균 심박수")
    @NotNull(message = "평균 심박수는 null 일 수 없습니다.")
    private Integer heartRate;
    @Schema(description = "평균 혈압")
    @NotNull(message = "평균 혈압은 null 일 수 없습니다.")
    private Integer bloodPressure;
}
