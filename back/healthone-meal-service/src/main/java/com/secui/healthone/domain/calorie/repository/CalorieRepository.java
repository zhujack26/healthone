package com.secui.healthone.domain.calorie.repository;

import com.secui.healthone.domain.calorie.entity.Calorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalorieRepository extends JpaRepository<Calorie, Integer> {
}
