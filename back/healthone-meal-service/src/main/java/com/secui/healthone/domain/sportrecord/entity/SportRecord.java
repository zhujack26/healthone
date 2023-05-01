package com.secui.healthone.domain.sportrecord.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "sport_no")
    private Integer sportNo;
    @Column(name = "customsport_no")
    private Integer customSportNo;
    @Column(name = "sportrecord_worktime")
    private Float workTime;
    @Column(name = "sportrecord_consume_calorie")
    private Integer consumeCalorie;
    @Column(name = "sportrecord_heartrate")
    private Integer heartRate;
    @Column(name = "sportrecord_blood_pressure")
    private Integer bloodPressure;
}
