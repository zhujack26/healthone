package com.secui.healthone.domain.sport.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customsport")
public class CustomSport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_no") // 사용자 운동 데이터 식별번호
    private Integer no;
    @Column(name = "user_no") // 회원 식별번호
    private Integer userNo;
    @Column(name = "sport_name") // 운동 이름
    private String name;
    @Column(name = "sport_consumekcal") // 시간당 소모 칼로리
    private String consumeKcal;
}
