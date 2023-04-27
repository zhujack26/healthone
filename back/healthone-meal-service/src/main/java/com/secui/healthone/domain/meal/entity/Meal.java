package com.secui.healthone.domain.meal.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "calorie_no") // 칼로리 식별번호
    private String calorieNo;
    @Column(name = "food_no") // 음식 식별번호
    private Integer foodNo;
    @Column(name = "customfood_no") // 사용자 음식 식별번호
    private Integer customfoodNo;
    @Column(name = "meal_portion") // 섭취한 인분 수
    private Float portion;
    @Column(name = "meal_gram") // 섭취한 gram 수
    private Float gram;
    @Column(name = "meal_kcal") // 섭취한 kcal 수
    private Float kcal;
}
