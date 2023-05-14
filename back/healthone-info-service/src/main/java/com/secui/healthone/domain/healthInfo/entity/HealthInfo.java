package com.secui.healthone.domain.healthInfo.entity;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
//    @Column(name = "health_info_profile_picture")
//    private String profilePicture;
    @Column(name = "health_info_nickname")
    private String nickname;
    @Column(name = "health_info_createtime")
    @CreationTimestamp
    private LocalDateTime createTime;
    @Column(name = "health_info_gender")
    private Boolean gender;
    @Column(name = "health_info_birthdate")
    private LocalDate birthdate;
    @Column(name = "health_info_height")
    private Integer height;
    @Column(name = "health_info_weight")
    private Integer weight;
    @Column(name = "health_info_work_rate")
    private String workRate;
    @Column(name = "health_info_step_goal")
    private Integer stepGoal;
    @Column(name = "health_info_sleep_time")
    private LocalTime sleepTime;
    @Column(name = "health_info_wakeup_time")
    private LocalTime wakeUpTime;
//    @Column(name = "health_info_sleep_goal")
//    private Integer sleepGoal;

    public void update (HealthInfoDto healthInfoDto){
        this.nickname = healthInfoDto.getNickname();
        this.gender = healthInfoDto.getGender();
        this.birthdate = healthInfoDto.getBirthdate();
        this.height = healthInfoDto.getHeight();
        this.weight = healthInfoDto.getWeight();
        this.workRate = healthInfoDto.getWorkRate();
        this.stepGoal = healthInfoDto.getStepGoal();
        this.sleepTime = healthInfoDto.getSleepTime();
        this.wakeUpTime = healthInfoDto.getWakeUpTime();
//        this.sleepGoal = healthInfoDto.getSleepGoal();
    }
}
