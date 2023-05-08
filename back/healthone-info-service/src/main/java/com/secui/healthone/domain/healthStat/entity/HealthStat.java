package com.secui.healthone.domain.healthStat.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_stat_no")
    private int no;
    @Column(name = "health_stat_user_no")
    private int userNo;
    @Column(name = "health_stat_createtime")
    private LocalDateTime createtime;
    @Column(name = "health_stat_height")
    private float height;
    @Column(name = "health_stat_weight")
    private float weight;
    @Column(name = "health_stat_bmi")
    private float bmi;
    @Column(name = "health_stat_body_fat_percentage")
    private float bodyFatPercentage;
    @Column(name = "health_stat_skeletal_muscle_mass")
    private float skeletalMuscleMass;
    @Column(name = "health_stat_tg")
    private float tg;
    @Column(name = "health_stat_hdl_cholesterol")
    private float hdlCholesterol;
    @Column(name = "health_stat_fbg")
    private float fbg;
    @Column(name = "health_stat_blood_pressure")
    private float bloodPressure;
    @Column(name = "health_stat_waist_circumference")
    private float waistCircumference;
}
