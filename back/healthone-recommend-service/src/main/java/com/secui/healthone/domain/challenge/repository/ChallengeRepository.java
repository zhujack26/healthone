package com.secui.healthone.domain.challenge.repository;

import com.secui.healthone.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    Optional<Challenge> findByNo(Integer no);
}
