package com.secui.healthone.domain.heartRate.entity;

import com.secui.healthone.global.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeartRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int heartRateNo;

    @ManyToOne
    private User user;

    private LocalDateTime heartRateCreatetime;

    private int heartRateCount;

}
