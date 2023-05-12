package com.secui.healthone.domain.healthInfo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
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
    @Column(name = "health_info_user_no")
    private Integer userNo;
    @Column(name = "health_info_gender")
    private boolean gender;
    @Column(name = "health_info_birthdate")
    private LocalDateTime birthdate;
    @Column(name = "health_info_height")
    private Float height;
    @Column(name = "health_info_weight")
    private Float weight;
    @Column(name = "health_info_work_rate")
    private String workRate;
    @Column(name = "health_info_step_goal")
    private Integer stepGoal;
    @Column(name = "health_info_sleep_time")
    private LocalDateTime sleepTime;
    @Column(name = "health_info_wake_up_time")
    private LocalDateTime wakeUpTime;
    @Column(name = "health_info_sleep_goal")
    private Integer sleepGoal;
}
