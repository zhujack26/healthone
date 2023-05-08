package com.secui.healthone.domain.healthInfo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GenerationType;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Schema(description = "건강 조언 DTO")
public class HealthInfoDto {
    private Integer no;
    private Integer userNo;
    private Boolean gender;
    private LocalDateTime birthdate;
    private Float height;
    private Float weight;
    private String workRate;
    private Integer stepGoal;
    private Time sleepTime;
    private Time wakeUpTime;
    private Integer sleepGoal;
}
