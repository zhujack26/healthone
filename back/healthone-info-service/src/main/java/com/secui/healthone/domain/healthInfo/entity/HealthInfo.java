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
    private int no;
    @Column(name = "health_info_user_no")
    private int userNo;
    @Column(name = "health_info_gender")
    private boolean gender;
    @Column(name = "health_info_birthdate")
    private LocalDateTime birthdate;
    @Column(name = "health_info_height")
    private float height;
    @Column(name = "health_info_weight")
    private float weight;
    @Column(name = "health_info_work_rate")
    private String workRate;
    @Column(name = "health_info_step_goal")
    private int stepGoal;
    @Column(name = "health_info_sleep_time")
    private Time sleepTime;
    @Column(name = "health_info_wake_up_time")
    private Time wakeUpTime;
    @Column(name = "health_info_sleep_goal")
    private int sleepGoal;
}
