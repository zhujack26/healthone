package com.secui.healthone.domain.sleep.entity;

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
public class Sleep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sleepNo;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime sleepCreatetime;

    private LocalDateTime sleepStartSleepTime;

    private LocalDateTime sleepEndSleepTime;

}
