package com.secui.healthone.domain.healthStat.api;

import com.secui.healthone.domain.healthStat.dto.HealthStatDto;
import com.secui.healthone.domain.healthStat.service.HealthStatService;
import com.secui.healthone.global.response.RestApiResponse;
import com.secui.healthone.global.util.HeaderUtil;
import com.secui.healthone.global.util.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-stat")
@Tag(name = "HealthStat", description = "건강 기록 관련 컨트롤러")
public class HealthStatController {

    private final HealthStatService healthStatService;
    private final TokenService tokenService;

    @Operation(summary = "건강 기록 조회 리스트", description = "회원 헬스 정보 조회 리스트 (한달) API", tags = {"HealthStat"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "건강 기록 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthStatDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "date", description = "건강 기록 조회 날짜", example = "2023-05-03T00:00:00")
    @GetMapping
    public RestApiResponse<List<HealthStatDto>> getHealthStat(@RequestHeader(required = false) String Authorization, @Valid @RequestParam String date) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("건강 기록 조회 성공", healthStatService.getHealthStat(date, userNo));
    }

    @Operation(summary = "건강 기록 등록", description = "건강 기록 등록 API", tags = {"HealthStat"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "건강 기록 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthStatDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "건강기록 등록 객체")
    @PostMapping
    public RestApiResponse<HealthStatDto> addHealthStat(@RequestHeader(required = false) String Authorization, @Valid @RequestBody HealthStatDto healthStatDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        healthStatDto.setUserNo(userNo);
        return new RestApiResponse<>("건강 기록 등록 성공", healthStatService.addHealthStat(healthStatDto));
    }

    @Operation(summary = "건강 기록 수정", description = "건강 기록 수정 API", tags = {"HealthStat"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "건강 기록 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthStatDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "건강기록 수정 객체")
    @PatchMapping
    public RestApiResponse<HealthStatDto> updateHealthStat(@RequestHeader(required = false) String Authorization, @Valid @RequestBody HealthStatDto healthStatDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        healthStatDto.setUserNo(userNo);
        return new RestApiResponse<>("건강 기록 수정 성공", healthStatService.updateHealthStat(healthStatDto));
    }

    @Operation(summary = "건강 기록 삭제", description = "건강 기록 삭제 API", tags = {"HealthStat"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "건강 기록 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "건강기록 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Integer> deleteHealthStat(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        healthStatService.deleteHealthStat(no, userNo);
        return new RestApiResponse<>("건강 기록 삭제 성공", no);
    }

}
