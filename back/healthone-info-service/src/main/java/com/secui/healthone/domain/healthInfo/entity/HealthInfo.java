package com.secui.healthone.domain.healthInfo.entity;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_info_no")
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo;
    @Column(name = "health_info_gender")
    @NotNull(message = "성별은 null 일 수 없습니다.")
    private Boolean gender;
    @Column(name = "health_info_birthdate")
    @NotNull(message = "생년월일은 null 일 수 없습니다.")
    private LocalDateTime birthdate;
    @Column(name = "health_info_height")
    @NotNull(message = "키는 null 일 수 없습니다.")
    private Float height;
    @Column(name = "health_info_weight")
    @NotNull(message = "체중은 null 일 수 없습니다.")
    private Float weight;
    @Column(name = "health_info_work_rate")
    @NotNull(message = "활동량은 null 일 수 없습니다.")
    private String workRate;
    @Column(name = "health_info_step_goal")
    @NotNull(message = "걸음 수 목표는 null 일 수 없습니다.")
    private Integer stepGoal;
    @Column(name = "health_info_sleep_time")
    @NotNull(message = "취침 목표 시간은 null 일 수 없습니다.")
    private LocalDateTime sleepTime;
    @Column(name = "health_info_wakeup_time")
    @NotNull(message = "기상 목표 시간은 null 일 수 없습니다.")
    private LocalDateTime wakeUpTime;
    @Column(name = "health_info_sleep_goal")
    @NotNull(message = "수면 목표 시간은 null 일 수 없습니다.")
    private Integer sleepGoal;

    public void update (HealthInfoDto healthInfoDto){
        this.no = healthInfoDto.getNo();
        this.userNo = healthInfoDto.getUserNo();
        this.gender = healthInfoDto.getGender();
        this.birthdate = healthInfoDto.getBirthdate();
        this.height = healthInfoDto.getHeight();
        this.weight = healthInfoDto.getWeight();
        this.workRate = healthInfoDto.getWorkRate();
        this.stepGoal = healthInfoDto.getStepGoal();
        this.sleepTime = healthInfoDto.getSleepTime();
        this.wakeUpTime = healthInfoDto.getWakeUpTime();
        this.sleepGoal = healthInfoDto.getSleepGoal();
    }
}
