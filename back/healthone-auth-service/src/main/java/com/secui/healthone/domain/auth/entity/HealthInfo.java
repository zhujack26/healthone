//package com.secui.healthone.domain.auth.entity;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@Builder
//public class HealthInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int healthInfoNo;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @Column(name = "user_no")
//    private User user;
//    private boolean healthInfoGender;
//    private LocalDateTime healthInfoBirthDate;
//    private float healthInfoHeight;
//    private float healthInfoWeight;
//    private String healthInfoWorkRate;
//    private int healthInfoStepGoal;
//    private int healthInfoSleepGoal;
//}
