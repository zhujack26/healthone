package com.secui.healthone.domain.participants.repository;

import com.secui.healthone.domain.participants.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<Participants, Integer> {
}
