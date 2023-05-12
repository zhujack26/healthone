package com.secui.healthone.domain.healthInfo.api;

import com.secui.healthone.domain.healthInfo.dto.HealthInfoDto;
import com.secui.healthone.domain.healthInfo.service.HealthInfoService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/health-info")
@Tag(name = "HealthInfo", description = "회원 헬스 정보 관련 컨트롤러")
public class HealthInfoController {

    private final HealthInfoService healthInfoService;

    @Operation(summary = "회원 헬스 정보 조회", description = "회원 헬스 정보 조회 API", tags = {"HealthInfo"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 정보 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RestApiResponse<HealthInfoDto> getHealthInfo(@RequestHeader(required = false) String Authorization) {
        Integer userNo = 1;
        HealthInfoDto healthInfoDto = healthInfoService.getHealthInfo(userNo);
        return new RestApiResponse<>("회원 헬스 정보 조회 성공", healthInfoDto);
    }

//    @Operation(summary = "회원 헬스 정보 등록", description = "회원 헬스 정보 등록 API", tags = {"HealthInfo"})
//    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 데이터 등록 성공", content = {
//            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
//            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
//    @SecurityRequirement(name = "bearerAuth")
//    @PostMapping
//    public RestApiResponse<HealthInfoDto> addHealthInfo(@RequestBody HealthInfoDto healthInfoDto) {
//        return new RestApiResponse<>("회원 헬스 데이터 등록 성공", healthInfoService.addHealthInfo(healthInfoDto));
//    }

    @Operation(summary = "회원 헬스 정보 등록, 수정", description = "회원 헬스 정보 등록, 수정 API", tags = {"HealthInfo"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 데이터 등록, 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping
    public RestApiResponse<HealthInfoDto> updateHealthInfo(@RequestBody HealthInfoDto healthInfoDto) {
        return new RestApiResponse<>("회원 헬스 데이터 등록, 수정 성공", healthInfoService.updateHealthInfo(healthInfoDto));
    }

    @Operation(summary = "회원 헬스 정보 삭제", description = "회원 헬스 정보 삭제 API", tags = {"HealthInfo"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 헬스 데이터 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthInfoDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public RestApiResponse<Integer> deleteHealthInfo(@RequestParam Integer no) {
        healthInfoService.deleteHealthInfo(no);
        return new RestApiResponse<>("회원 헬스 데이터 삭제 성공", no);
    }

}
