package com.secui.healthone.domain.stress.repository;

import com.secui.healthone.domain.stress.entity.Stress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StressRepository extends JpaRepository<Stress, Integer> {
}
