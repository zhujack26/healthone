package com.secui.healthone.domain.calorie.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "calorie")
public class Calorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    @Column(name = "user_no")
    private Integer userNo;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "meal_type")
    private MealType mealType;
    @Column(name = "createtime")
    private LocalDateTime createTime;
}
