package com.secui.healthone.domain.challenge.repository;

import com.secui.healthone.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
}
