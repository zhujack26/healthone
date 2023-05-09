package com.secui.healthone.domain.participants.api;

import com.secui.healthone.domain.challenge.dto.ChallengeResDto;
import com.secui.healthone.domain.participants.dto.ParticipantsCommonDto;
import com.secui.healthone.domain.participants.service.ParticipantsService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "챌린지 참가 등록", description = "챌린지 참가 등록 API", tags = {"Participants"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "챌린지 참가 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantsCommonDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "챌린지 등록 객체", required = true,
            content = @Content(schema = @Schema(implementation = ParticipantsCommonDto.class)))
    @PostMapping
    public RestApiResponse<ParticipantsCommonDto> insert(@RequestBody @Valid ParticipantsCommonDto participantsCommonDto) {
        return new RestApiResponse<>("챌린지 참가 등록 성공", participantsService.insert(participantsCommonDto));
    }

    // 챌린지 참가 취소
    @DeleteMapping
    public RestApiResponse<Void> delete(@RequestBody @Valid ParticipantsCommonDto participantsCommonDto) {
        participantsService.delete(participantsCommonDto);
        return new RestApiResponse<>("챌린지 참가 삭제 성공", null);
    }



}
