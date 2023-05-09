package com.secui.healthone.domain.participants.api;

import com.secui.healthone.domain.participants.dto.ParticipantsCommonDto;
import com.secui.healthone.domain.participants.service.ParticipantsService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
@Tag(name = "Participants", description = "챌린지 참가 관련 컨트롤러")
public class ParticipantsController {

    private final ParticipantsService participantsService;

    // 챌린지 참가
    @PostMapping
    public RestApiResponse<?> insert(@RequestBody @Valid ParticipantsCommonDto participantsCommonDto) {
        return null;
    }

    // 챌린지 참가 취소
    @DeleteMapping
    public RestApiResponse<?> delete(@RequestBody @Valid ParticipantsCommonDto participantsCommonDto) {
        return null;
    }



}
