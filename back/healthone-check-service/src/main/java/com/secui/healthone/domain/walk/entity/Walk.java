package com.secui.healthone.domain.walk.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "walk")
public class Walk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walk_no") // 걷기 식별번호
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo;
    @Column(name = "walk_createtime")
    @CreationTimestamp
    private LocalDateTime createtime;
    @Column(name = "walk_step_count")
    private Integer stepCount;
    @Column(name = "walk_distance")
    private Float distance;
}
