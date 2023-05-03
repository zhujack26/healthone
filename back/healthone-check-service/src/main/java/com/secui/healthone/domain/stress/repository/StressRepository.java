package com.secui.healthone.domain.stress.repository;

import com.secui.healthone.domain.stress.entity.Stress;
import com.secui.healthone.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StressRepository extends JpaRepository<Stress, Integer> {

    List<Stress> findAllByUserAndStressCreatetimeBeforeOrderByStressCreatetime(User user, LocalDateTime localDateTime);

}
