package com.secui.healthone.domain.challenge.repository;

import com.secui.healthone.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    // 단일 챌린지 정보 가져오기
    Optional<Challenge> findByNo(Integer no);

    // 많이 참여한 챌린지 정보 7개 반환
    List<Challenge> findTop7ByOrderByParticipantsDesc();

    // 인기있는 챌린지 정보 7개 반환 (조회수 기준)
    List<Challenge> findTop7ByOrderByHitsDesc();

}
