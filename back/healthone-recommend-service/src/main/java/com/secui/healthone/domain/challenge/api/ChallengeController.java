package com.secui.healthone.domain.challenge.api;

import com.secui.healthone.domain.challenge.service.ChallengeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
@Tag(name = "Challenge", description = "챌린지 관련 컨트롤러")
public class ChallengeController {
    private final ChallengeService challengeService;
}
