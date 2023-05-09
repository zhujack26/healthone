package com.secui.healthone.domain.participants.service;

import com.secui.healthone.domain.participants.dto.ParticipantsCommonDto;
import com.secui.healthone.domain.participants.entity.Participants;
import com.secui.healthone.domain.participants.repository.ParticipantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantsService {

    private final ParticipantsRepository participantsRepository;

    // 참가 등록
    public ParticipantsCommonDto insert(ParticipantsCommonDto participantsCommonDto) {
        return new ParticipantsCommonDto(participantsRepository.save(participantsCommonDto.toEntity()));
    }

    // 참가 제거
    public void delete(ParticipantsCommonDto participantsCommonDto) {
        participantsRepository.delete(participantsCommonDto.toEntity());
    }
}
