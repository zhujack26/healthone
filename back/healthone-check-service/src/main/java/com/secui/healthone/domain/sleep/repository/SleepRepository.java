package com.secui.healthone.domain.sleep.repository;

import com.secui.healthone.domain.sleep.entity.Sleep;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SleepRepository extends JpaRepository<Sleep, Integer> {
//    List<Sleep> findAllByUserAndSleepCreatetimeLike(User user, String date);
//    List<Sleep> findAllByUserNoAndCreateTimeLike(int userNo, String date);\

    // 해당 날짜 수면 리스트 반환
    List<Sleep> findByCreateTimeBetweenAndUserNo(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer userNo);

    // 해당 날짜 수면 7일 데이터 반환 날짜 오름차순
    List<Sleep> findByCreateTimeBetweenAndUserNoOrderByCreateTimeDesc(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer userNo);
//    Slice<Sleep> findAllByUserNoOrderByCreateTimeDesc(Integer userNo, Pageable pageable);
    Optional<Sleep> findByNoAndUserNo(Integer no, Integer userNo);
    Sleep save(Sleep sleep);
    void deleteByNoAndUserNo(Integer no, Integer userNo);
}
