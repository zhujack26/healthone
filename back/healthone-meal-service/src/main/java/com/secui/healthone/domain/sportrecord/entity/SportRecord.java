package com.secui.healthone.domain.sportrecord.entity;

import com.secui.healthone.domain.sportrecord.dto.SportRecordReqDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sportrecord")
public class SportRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportrecord_no")
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo;
    @Column(name = "sportrecord_name")
    private String name;
    @Column(name = "sportrecord_createtime")
    private LocalDateTime createTime;
    @Column(name = "sportrecord_worktime")
    private Float workTime;
    @Column(name = "sportrecord_consume_calorie")
    private Integer consumeCalorie;
    @Column(name = "sportrecord_heartrate")
    private Integer heartRate;
    @Column(name = "sportrecord_blood_pressure")
    private Integer bloodPressure;

    public void update(SportRecordReqDto sportRecordReqDto){
        this.name = sportRecordReqDto.getName();
        this.workTime = sportRecordReqDto.getWorkTime();
        this.consumeCalorie = sportRecordReqDto.getConsumeCalorie();
        this.heartRate = sportRecordReqDto.getHeartRate();
        this.bloodPressure = sportRecordReqDto.getBloodPressure();
    }
}
