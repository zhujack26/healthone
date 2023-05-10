package com.secui.healthone.domain.participants.api;

import com.secui.healthone.domain.participants.dto.ParticipantsInsertDto;
import com.secui.healthone.domain.participants.dto.ParticipantsResDto;
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
            @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantsInsertDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "챌린지 등록 객체", required = true,
            content = @Content(schema = @Schema(implementation = ParticipantsInsertDto.class)))
    @PostMapping
    public RestApiResponse<ParticipantsResDto> insert(@RequestBody ParticipantsInsertDto participantsInsertDto) {
        return new RestApiResponse<>("챌린지 참가 등록 성공", participantsService.insert(participantsInsertDto));
    }

    // 챌린지 참가 취소
    @Operation(summary = "챌린지 참가 취소", description = "챌린지 참가 취소 API", tags = {"Participants"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "챌린지 참가 취소 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantsInsertDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @Parameter(name = "no", description = "참가번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Void> delete(@Valid @RequestParam("no") Integer no) {
        participantsService.delete(no);
        return new RestApiResponse<>("챌린지 참가 취소 성공", null);
    }

}
