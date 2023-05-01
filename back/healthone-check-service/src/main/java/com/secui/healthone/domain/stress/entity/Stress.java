package com.secui.healthone.domain.stress.entity;

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
public class Stress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stressNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Column(nullable = false)
    private int stressLevel;

    @Column(nullable = false)
    private LocalDateTime stressCreatetime;

}
