package com.secui.healthone.domain.healthStat.entity;

import com.secui.healthone.domain.healthStat.dto.HealthStatDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "health_stat")
@Builder
public class HealthStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_stat_no")
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo;
    @Column(name = "health_stat_createtime")
    private LocalDateTime createTime;
    @Column(name = "health_stat_height")
    private Float height;
    @Column(name = "health_stat_weight")
    private Float weight;
    @Column(name = "health_stat_bmi")
    private Float bmi;
    @Column(name = "health_stat_body_fat_percentage")
    private Float bodyFatPercentage;
    @Column(name = "health_stat_skeletal_muscle_mass")
    private Float skeletalMuscleMass;
    @Column(name = "health_stat_waist_measurement")
    private Float waistMeasurement;
    @Column(name = "health_stat_low_blood_pressure")
    private Float lowBloodPressure;
    @Column(name = "health_stat_high_blood_pressure")
    private Float highBloodPressure;
    @Column(name = "health_stat_fbg")
    private Float fbg;
    @Column(name = "health_stat_hdl_cholesterol")
    private Float hdlCholesterol;
    @Column(name = "health_stat_tg")
    private Float tg;

    public void update(HealthStatDto healthStatDto) {
        this.createTime = healthStatDto.getCreateTime();
        this.height = healthStatDto.getHeight();
        this.weight = healthStatDto.getWeight();
        this.bmi = healthStatDto.getBmi();
        this.bodyFatPercentage = healthStatDto.getBodyFatPercentage();
        this.skeletalMuscleMass = healthStatDto.getSkeletalMuscleMass();
        this.waistMeasurement = healthStatDto.getWaistMeasurement();
        this.lowBloodPressure = healthStatDto.getLowBloodPressure();
        this.highBloodPressure = healthStatDto.getHighBloodPressure();
        this.fbg = healthStatDto.getFbg();
        this.hdlCholesterol = healthStatDto.getHdlCholesterol();
        this.tg = healthStatDto.getTg();
    }
}
