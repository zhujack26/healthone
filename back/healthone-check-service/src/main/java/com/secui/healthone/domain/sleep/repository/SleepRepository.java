package com.secui.healthone.domain.sleep.repository;

import com.secui.healthone.domain.sleep.entity.Sleep;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SleepRepository extends JpaRepository<Sleep, Integer> {
//    List<Sleep> findAllByUserAndSleepCreatetimeLike(User user, String date);
    List<Sleep> findAllByUserNoAndCreatetimeLike(int userNo, String date);

    Slice<Sleep> findAllByUserNoOrderByCreatetimeDesc(Integer userNo, Pageable pageable);
}
