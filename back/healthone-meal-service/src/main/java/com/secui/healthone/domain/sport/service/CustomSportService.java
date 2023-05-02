package com.secui.healthone.domain.sport.service;

import com.secui.healthone.domain.sport.dto.CustomSportMapper;
import com.secui.healthone.domain.sport.dto.CustomSportReqDto;
import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import com.secui.healthone.domain.sport.entity.CustomSport;
import com.secui.healthone.domain.sport.repository.CustomSportRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomSportService {
    private final CustomSportRepository customSportRepository;
    private final CustomSportMapper customSportMapper;

    // 사용자 운동데이터 검색 (리스트)
    public List<CustomSportResDto> searchCustomSport(Integer userNo, String name) {
        List<CustomSport> result = customSportRepository.findByUserNoAndNameContaining(userNo, name);
        return customSportMapper.customSportToCustomSportResListDto(result);
    }

    // 사용자 운동 데이터 단건 조회
    public CustomSportResDto getCustomSport(Integer userNo, Integer no) {
        Optional<CustomSport> result = customSportRepository.findByNoAndUserNo(userNo, no);
        return customSportMapper.customSportToCustomSportResDto(result.orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100)));
    }

    // 사용자 운동 데이터 단건 등록, 수정
    @Transactional
    public CustomSportResDto insertCustomSport(CustomSportReqDto reqDto) {
        customSportRepository.save(customSportMapper.customSportReqDtoToCustomSport(reqDto));
        return customSportMapper.customSportToCustomSportResDto(customSportMapper.customSportReqDtoToCustomSport(reqDto));
    }

    // 운동 데이터 삭제
    @Transactional
    public void deleteCustomSport(CustomSportReqDto reqDto) {
        customSportRepository.deleteById(customSportMapper.customSportReqDtoToCustomSport(reqDto).getNo());
    }
}
