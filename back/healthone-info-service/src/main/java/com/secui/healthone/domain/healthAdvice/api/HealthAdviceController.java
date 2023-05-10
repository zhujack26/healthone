package com.secui.healthone.domain.healthAdvice.api;

import com.secui.healthone.domain.healthAdvice.dto.GetHealthAdviceReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDeleteReqDto;
import com.secui.healthone.domain.healthAdvice.dto.HealthAdviceDto;
import com.secui.healthone.domain.healthAdvice.service.HealthAdviceService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-adivce")
@Tag(name = "HealthAdvice", description = "회원 조언 컨트롤러")
public class HealthAdviceController {

    private final HealthAdviceService healthAdviceService;

    @Operation(summary = "회원 건강 조언 조회", description = "회원 건강 조언 조회 API", tags = {"HealthAdvice"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 건강 조언 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HealthAdviceDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RestApiResponse<List<HealthAdviceDto>> getHealthAdvice(@RequestBody GetHealthAdviceReqDto getHealthAdviceReqDto) {
        List<HealthAdviceDto> healthAdviceResDtoList = healthAdviceService.getHealthAdvice(getHealthAdviceReqDto);
        return new RestApiResponse<>("회원 건강 조언 조회 성공", healthAdviceResDtoList);
    }

    @Operation(summary = "회원 건강 조언 삭제", description = "회원 건강 조언 삭제 API", tags = {"HealthAdvice"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 건강 조언 삭제 성공", content = {
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public RestApiResponse<Void> deleteHealthAdvice(@RequestBody HealthAdviceDeleteReqDto healthAdviceDeleteReqDto) {
        healthAdviceService.deleteHealthAdvice(healthAdviceDeleteReqDto);
        return new RestApiResponse<>("회원 건강 조언 삭제 성공", null);
    }
}
