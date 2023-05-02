package com.secui.healthone.domain.sport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Schema(description = "사용자 운동 데이터 요청(등록, 수정) DTO")
public class CustomSportReqDto {
    @Schema(description = "사용자 운동 데이터 식별번호")
    private Integer no;
    @Schema(description = "회원식별번호")
    private Integer userNo;
    @Schema(description = "사용자 운동 이름")
    private String name;
    @Schema(description = "사용자 운동 시간당 소비 칼로리")
    private String consumeKcal;
}
