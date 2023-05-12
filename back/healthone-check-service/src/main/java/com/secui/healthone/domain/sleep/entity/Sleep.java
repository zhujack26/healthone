package com.secui.healthone.domain.sleep.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sleep")
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sleep_no") // 수면 데이터 식별번호
    private Integer no;
    @Column(name = "user_no") // 회원 식별 번호
    private Integer userNo;
    @Column(name = "sleep_createtime") // 수면 날짜
    private LocalDateTime createTime;
    @Column(name = "sleep_start_sleep_time") // 수면 시작 시간
    private LocalDateTime startSleepTime;
    @Column(name = "sleep_end_sleep_time") // 수면 기상 시간
    private LocalDateTime endSleepTime;
}
