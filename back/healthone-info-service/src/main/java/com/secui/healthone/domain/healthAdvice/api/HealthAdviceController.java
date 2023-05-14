package com.secui.healthone.domain.healthAdvice.api;

import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthAdvice.service.HealthAdviceService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-advice")
@Tag(name = "HealthAdvice", description = "회원 조언 컨트롤러")
public class HealthAdviceController {

    private final HealthAdviceService healthAdviceService;
    private final TokenService tokenService;

    @Operation(summary = "회원 건강 조언 조회", description = "회원 건강 조언 조회 (가장 최신) API", tags = {"HealthAdvice"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 건강 조언 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthAdviceDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = ErrorResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping
    public RestApiResponse<HealthAdviceDto> getHealthAdvice(@RequestHeader(required = false) String Authorization) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        HealthAdviceDto healthAdviceResDto = healthAdviceService.getHealthAdvice(userNo);
        return new RestApiResponse<>("회원 건강 조언 조회 성공", healthAdviceResDto);
    }

//    @Operation(summary = "회원 건강 조언 삭제", description = "회원 건강 조언 삭제 API", tags = {"HealthAdvice"})
//    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 건강 조언 삭제 성공", content = {
//            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
//    @SecurityRequirement(name = "bearerAuth")
//    @DeleteMapping
//    public RestApiResponse<Void> deleteHealthAdvice(@RequestBody HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
//        healthAdviceService.deleteHealthAdvice(healthAdviceDeleteReqDto);
//        return new RestApiResponse<>("회원 건강 조언 삭제 성공", null);
//    }
}
