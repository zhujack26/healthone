package com.secui.healthone.domain.healthInfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "회원 헬스정보 DTO")
public class HealthInfoDto {
    @Schema(description = "회원 헬스 정보 식별번호")
    private Integer no;
    @Schema(description = "회원 식별번호")
    private Integer userNo;
    @Schema(description = "회원 닉네임")
    @NotNull(message = "닉네임은 null 일 수 없습니다.")
    private String nickname;
    @Schema(description = "회원 가입일시")
    private LocalDateTime createTime;
    @Schema(description = "성별")
    @NotNull(message = "성별은 null 일 수 없습니다.")
    private Boolean gender;
    @Schema(description = "생년월일")
    @NotNull(message = "생년월일은 null 일 수 없습니다.")
    private LocalDate birthdate;
    @Schema(description = "키")
    @NotNull(message = "키는 null 일 수 없습니다.")
    private Integer height;
    @Schema(description = "몸무게")
    @NotNull(message = "체중은 null 일 수 없습니다.")
    private Integer weight;
    @Schema(description = "활동량")
    @NotNull(message = "활동량은 null 일 수 없습니다.")
    private String workRate;
    @Schema(description = "목표 걸음 수")
    @NotNull(message = "걸음 수 목표는 null 일 수 없습니다.")
    private Integer stepGoal;
    @Schema(description = "목표 취침 시간")
    @NotNull(message = "취침 목표 시간은 null 일 수 없습니다.")
    private LocalTime sleepTime;
    @Schema(description = "목표 기상 시간")
    @NotNull(message = "기상 목표 시간은 null 일 수 없습니다.")
    private LocalTime wakeUpTime;
//    @Schema(description = "목표 수면 시간")
//    private Integer sleepGoal;
}
