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
    @Column(name = "user_no") // 회원 식별번호
    private Integer userNo;
    @Column(name = "meal_name")
    private String name;
    @Column(name = "meal_createtime") // 사용자 음식 식별번호
    private LocalDateTime createTime;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "meal_type")
    private MealType mealType;
//    @Column(name = "meal_portion") // 섭취한 인분 수
//    private Float portion;
    @Column(name = "meal_gram") // 섭취한 gram 수
    private Float gram;
    @Column(name = "meal_kcal") // 섭취한 kcal 수
    private Integer kcal;

    public void update(Meal meal) {
//        this.food = meal.getFood() == null ? this.food : meal.getFood() ;
//        this.customfood = meal.getCustomfood() == null ? this.customfood : meal.getCustomfood();
        this.name = meal.getName();
        this.createTime = meal.getCreateTime();
        this.mealType = meal.getMealType();
//        this.portion = meal.getPortion();
        this.gram = meal.getGram();
        this.kcal = meal.getKcal();
    }
}
