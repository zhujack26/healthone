package com.secui.healthone.domain.healthInfo.api;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import com.secui.healthone.domain.healthInfo.service.HealthInfoService;
import com.secui.healthone.global.error.response.ErrorResponse;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/health-info")
@Tag(name = "HealthInfo", description = "회원 헬스 정보 관련 컨트롤러")
public class HealthInfoController {

    private final HealthInfoService healthInfoService;
    private final TokenService tokenService;

    @Operation(summary = "회원 헬스 정보 조회", description = "회원 헬스 정보 조회 API", tags = {"HealthInfo"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 정보 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = ErrorResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping
    public RestApiResponse<HealthInfoDto> getHealthInfo(@RequestHeader(required = false) String Authorization) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        HealthInfoDto healthInfoDto = healthInfoService.getHealthInfo(userNo);
        return new RestApiResponse<>("회원 헬스 정보 조회 성공", healthInfoDto);
    }

    @Operation(summary = "회원 헬스 정보 등록, 수정", description = "회원 헬스 정보 등록, 수정 API (처음 요청시 DB에 값을 저장함)", tags = {"HealthInfo"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 데이터 등록, 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = ErrorResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원 헬스 데이터 등록, 수정 객체")
    @PatchMapping
    public RestApiResponse<HealthInfoDto> updateHealthInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestBody HealthInfoDto healthInfoDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        healthInfoDto.setUserNo(userNo);
        return new RestApiResponse<>("회원 헬스 데이터 등록, 수정 성공", healthInfoService.updateHealthInfo(healthInfoDto));
    }

//    @Operation(summary = "회원 헬스 정보 삭제", description = "회원 헬스 정보 삭제 API", tags = {"HealthInfo"})
//    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 데이터 수정 성공", content = {
//            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
//            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
//    @SecurityRequirement(name = "bearerAuth")
//    @DeleteMapping
//    public RestApiResponse<Integer> deleteHealthInfo(@RequestParam Integer no) {
//        healthInfoService.deleteHealthInfo(no);
//        return new RestApiResponse<>("회원 헬스 데이터 삭제 성공", no);
//    }

}
