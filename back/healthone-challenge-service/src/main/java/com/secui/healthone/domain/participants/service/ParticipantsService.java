package com.secui.healthone.domain.participants.service;

import com.secui.healthone.domain.participants.dto.ParticipantsInsertDto;
import com.secui.healthone.domain.participants.dto.ParticipantsResDto;
import com.secui.healthone.domain.participants.repository.ParticipantsRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantsService {

    private final ParticipantsRepository participantsRepository;

    // 참가 등록
    public ParticipantsResDto insert(ParticipantsInsertDto participantsInsertDto) {
        return new ParticipantsResDto(participantsRepository.save(participantsInsertDto.toEntity()));
    }

    // 참가 제거
    public void delete(Integer no, Integer userNo) {
        participantsRepository.findByNoAndUserNo(no, userNo).orElseThrow(() -> new RestApiException(CustomErrorCode.DB_100));
        participantsRepository.deleteByNoAndUserNo(no, userNo);
    }
}
