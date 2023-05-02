package com.secui.healthone.domain.sportrecord.entity;

import com.secui.healthone.domain.sport.entity.CustomSport;
import com.secui.healthone.domain.sport.entity.Sport;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_no")
    private Sport sport;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customsport_no")
    private CustomSport customSport;
    @Column(name = "sportrecord_creattime")
    private LocalDateTime createTime;
    @Column(name = "sportrecord_worktime")
    private Float workTime;
    @Column(name = "sportrecord_consume_calorie")
    private Integer consumeCalorie;
    @Column(name = "sportrecord_heartrate")
    private Integer heartRate;
    @Column(name = "sportrecord_blood_pressure")
    private Integer bloodPressure;
}
