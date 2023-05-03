package com.secui.healthone.domain.heartRate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "heart_rate")
public class HeartRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_rate_no")
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo;
    @Column(name = "heart_rate_createtime")
    private LocalDateTime createTime;
    @Column(name = "heart_rate_count")
    private Integer count;
}
