package com.secui.healthone.domain.sport.repository;

import com.secui.healthone.domain.sport.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport, Integer> {

    List<Sport> findAllByNameContaining(String name);
}
