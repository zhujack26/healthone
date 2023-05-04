package com.secui.healthone.domain.walk.repository;

import com.secui.healthone.domain.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Integer> {

    List<Walk> findAllByUserNoAndCreatetimeBetween(int user, LocalDateTime createtime1, LocalDateTime createtime2);
//    List<Walk> findAllByUserAndWalkCreatetimeBetween(User user, LocalDateTime walkCreatetime1, LocalDateTime walkCreatetime2);

    List<Walk> findAllByUserNoAndCreatetime(int userNo, LocalDateTime createtime);
//    List<Walk> findAllByUserAndWalkCreatetime(User user, LocalDateTime walkCreatetime);

    Walk save(Walk walk);

}
