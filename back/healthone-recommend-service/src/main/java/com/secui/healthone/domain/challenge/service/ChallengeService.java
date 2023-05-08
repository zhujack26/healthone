package com.secui.healthone.domain.challenge.service;

import com.secui.healthone.domain.challenge.dto.ChallengeResDto;
import com.secui.healthone.domain.challenge.entity.Challenge;
import com.secui.healthone.domain.challenge.repository.ChallengeRepository;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    public ChallengeResDto getChallenge(Integer no) {
//        Challenge result = challengeRepository.findByNo(no).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100));
        return new ChallengeResDto(challengeRepository.findByNo(no).orElseThrow(()-> new RestApiException(CustomErrorCode.DB_100)));
    }
}
