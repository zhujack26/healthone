package com.secui.healthone.domain.walk.entity;

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
@ToString
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walkNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(nullable = false)
    private LocalDateTime walkCreatetime;

    @Column(nullable = false)
    private int walkStepCount;

    private float walkDistance;

}
