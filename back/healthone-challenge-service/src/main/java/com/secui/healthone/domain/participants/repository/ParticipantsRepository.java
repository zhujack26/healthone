package com.secui.healthone.domain.participants.repository;

import com.secui.healthone.domain.participants.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipantsRepository extends JpaRepository<Participants, Integer> {
    
    // 전체 참가한 챌린지 사람 수 출력
    @Query("SELECT COUNT(DISTINCT p.userNo) FROM Participants p")
    Integer countDistinctByUserNo();
    // 회원 챌린지 참가 횟수 출력
    Integer countByUserNoEquals(Integer userNo);

    // 챌린지 참가
    Participants save(Participants participants);

    Optional<Participants> findByNoAndUserNo(Integer no, Integer userNo);
    void deleteByNoAndUserNo(Integer no, Integer userNo);

}
