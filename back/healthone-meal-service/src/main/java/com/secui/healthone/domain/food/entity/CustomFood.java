package com.secui.healthone.domain.food.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "customfood")
public class CustomFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customfood_no") // 음식 데이터 식별번호
    private Integer no;
    @Column(name = "user_no", nullable = false) // 회원식별번호
    private Integer userNo;
    @Column(name = "customfood_name", nullable = false) // 음식 이름
    private String name;
    @Column(name = "customfood_kcal", nullable = false) // 1인분당 칼로리
    private Integer kcal;
    @Column(name = "customfood_gram", nullable = false) // 1인분 gram수
    private Float gram;
    public void update(String name, Integer kcal, Float gram) {
        this.name = name;
        this.kcal = kcal;
        this.gram = gram;
    }
}
