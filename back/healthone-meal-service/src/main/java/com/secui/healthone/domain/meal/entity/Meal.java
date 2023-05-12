package com.secui.healthone.domain.meal.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_no") // 식사 식별번호
    private Integer no;
    @Column(name = "user_no", nullable = false) // 회원 식별번호
    private Integer userNo;
    @Column(name = "meal_name", nullable = false)
    private String name;
    @Column(name = "meal_createtime", nullable = false) // 사용자 음식 등록 날짜
    private LocalDateTime createTime;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;
    @Column(name = "meal_gram") // 섭취한 gram 수
    private Float gram;
    @Column(name = "meal_kcal") // 섭취한 kcal 수
    private Integer kcal;

    public void update(Meal meal) {
        this.name = meal.getName();
        this.createTime = meal.getCreateTime();
        this.mealType = meal.getMealType();
        this.gram = meal.getGram();
        this.kcal = meal.getKcal();
    }
}
