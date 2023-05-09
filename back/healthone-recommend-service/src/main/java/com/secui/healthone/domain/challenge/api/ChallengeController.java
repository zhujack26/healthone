package com.secui.healthone.domain.challenge.api;

import com.secui.healthone.domain.challenge.dto.ChallengeResDto;
import com.secui.healthone.domain.challenge.service.ChallengeService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
@Tag(name = "Challenge", description = "챌린지 관련 컨트롤러")
@Slf4j
public class ChallengeController {
    private final ChallengeService challengeService;

    // 인기 있는 운동 챌린지 반환, 많이 찾는 운동 컨텐츠 반환, 전체 챌린지 참여 인원, 회원이 지금까지 참여한 챌린지 횟수
    @Operation(summary = "챌린지 메인페이지 정보 조회", description = "챌린지 메인페이지 정보를 반환한다.", tags = {"Challenge"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "챌린지 메인페이지 데이터 반환 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ChallengeResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @Parameter(name = "no", description = "챌린지 식별번호", example = "1")
    @GetMapping
    public RestApiResponse<ChallengeResDto> getChallengeMainPageData () {
        Integer userNo = 1;
        return new RestApiResponse<>("챌린지 메인페이지 데이터 반환 성공", challengeService.getChallenge(userNo));
    }

    // 세부 챌린지 정보 반환
    @Operation(summary = "챌린지 세부 정보 조회", description = "챌린지 세부 정보를 반환한다.", tags = {"Challenge"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "챌린지 세부 정보를 반환 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ChallengeResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @Parameter(name = "no", description = "챌린지 식별번호", example = "1")
    @GetMapping
    public RestApiResponse<ChallengeResDto> getChallengeData (@RequestParam("no") Integer no) {
        log.info("no : {}", no);
        return new RestApiResponse<>("챌린지 데이터 반환 성공", challengeService.getChallenge(no));
    }
}
