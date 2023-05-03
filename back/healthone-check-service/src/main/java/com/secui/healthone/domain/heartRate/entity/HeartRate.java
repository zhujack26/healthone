package com.secui.healthone.domain.heartRate.entity;

import com.secui.healthone.domain.user.entity.User;
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
    private int no;

    private int userNo;

    private LocalDateTime createtime;

    private int count;

}
