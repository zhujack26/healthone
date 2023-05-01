package com.secui.healthone.domain.walk.repository;

import com.secui.healthone.domain.walk.entity.User;
import com.secui.healthone.domain.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Integer> {

    List<Walk> findAllByUserAndWalkCreatetimeBetween(User user, LocalDateTime walkCreatetime1, LocalDateTime walkCreatetime2);

    List<Walk> findAllByUserAndWalkCreatetime(User user, LocalDateTime walkCreatetime);

}
