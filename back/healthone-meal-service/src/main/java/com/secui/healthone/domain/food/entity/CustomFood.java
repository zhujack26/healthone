package com.secui.healthone.domain.food.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "customfood")
public class CustomFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_no") // 음식 데이터 식별번호
    private Integer no;
    @Column(name = "user_no") // 회원식별번호
    private Integer userNo;
    @Column(name = "food_name") // 음식 이름
    private String name;
    @Column(name = "food_kcal") // 1인분당 칼로리
    private Integer kcal;
    @Column(name = "food_gram") // 1인분 gram수
    private Float gram;
}
