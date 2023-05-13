package com.secui.healthone.domain.challenge.service;

import com.secui.healthone.domain.challenge.dto.ChallengeMainResDto;
import com.secui.healthone.domain.challenge.dto.ChallengeResDto;
import com.secui.healthone.domain.challenge.entity.Challenge;
import com.secui.healthone.domain.challenge.repository.ChallengeRepository;
import com.secui.healthone.domain.participants.repository.ParticipantsRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ParticipantsRepository participantsRepository;

    public ChallengeMainResDto getChallengeMainPageData(Integer userNo) {
        List<Challenge> getPopularList = challengeRepository.findTop7ByOrderByParticipantsDesc();
        List<Challenge> getFrequentlyList = challengeRepository.findTop7ByOrderByHitsDesc();
        Integer totalChallengeParticipantsCount = participantsRepository.countDistinctByUserNo();
        Integer userChallengeParticipantsCount = participantsRepository.countByUserNoEquals(userNo);

        return ChallengeMainResDto.builder()
                .popularChallengeList(getPopularList.stream().map(ChallengeResDto::new).collect(Collectors.toList()))
                .frequentlyChallengeList(getFrequentlyList.stream().map(ChallengeResDto::new).collect(Collectors.toList()))
                .totalChallengeParticipantsCount(totalChallengeParticipantsCount)
                .userChallengeParticipantsCount(userChallengeParticipantsCount)
                .build();
    }
    
    // 세부 페이지 반환
    public ChallengeResDto getChallenge(Integer no) {
        Challenge result = challengeRepository.findByNo(no).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        result.setHits(result.getHits() + 1);
        return new ChallengeResDto(result);
    }
}
