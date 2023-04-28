package com.secui.healthone.domain.sportrecord.repository;

import com.secui.healthone.domain.sportrecord.entity.SportRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<SportRecord, Integer> {

}
