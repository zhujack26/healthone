package com.secui.healthone.domain.sport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "사용자 운동 데이터 요청(등록, 수정) DTO")
public class CustomSportReqDto {
    @Schema(description = "사용자 운동 데이터 식별번호")
    private Integer no;
    @Schema(description = "회원식별번호")
    private Integer userNo;
    @Schema(description = "사용자 운동 이름")
    @NotNull(message = "운동이름은 null 일 수 없습니다.")
    private String name;
    @Schema(description = "사용자 운동 시간당 소비 칼로리")
    @NotNull(message = "시간당 소비 칼로리는 null 일 수 없습니다.")
    private Integer consumeKcal;
}
