package com.secui.healthone.domain.heartRate.api;

import com.secui.healthone.domain.heartRate.dto.HeartRateInsertDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
import com.secui.healthone.global.response.RestApiResponse;
import com.secui.healthone.global.util.HeaderUtil;
import com.secui.healthone.global.util.TokenService;
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
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/heart-rate")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "HeartRate", description = "심박수 관련 컨트롤러")
public class HeartRateController {
    private final HeartRateService heartRateService;
    private final TokenService tokenService;

    @Operation(summary = "심박수 리스트 조회", description = "회원의 심박수를 최신순으로 출력한다", tags = {"HeartRate"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HeartRateResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @Parameter(in = ParameterIn.QUERY, description = "페이지 번호 (0..N)", name = "page", example = "0",
            content = @Content(schema = @Schema(type = "Integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY, description = "페이지 크기", name = "size", example = "7",
            content = @Content(schema = @Schema(type = "Integer", defaultValue = "7")))
    @GetMapping
    public RestApiResponse<Slice<HeartRateResDto>> getHeartRateInfo(@RequestHeader(required = false) String Authorization, @ParameterObject Pageable pageable) {
        log.info("/api/heart-rate (GET) | 심박수 리스트 조회 요청됨");
        log.info("Authorization : {}", Authorization);
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        log.info("accessToken : {}", accessToken);
        Integer userNo = tokenService.getUserNo(accessToken);
        log.info("userNo : {}", userNo);
        return new RestApiResponse<>(pageable.getPageNumber()+"페이지 심박수 리스트 조회 완료", heartRateService.getHeartRateList(userNo, pageable));
    }

    @Operation(summary = "심박수 등록", description = "회원의 심박수를 등록한다", tags = {"HeartRate"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "심박수 추가 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HeartRateResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "심박수 등록 객체")
    @PostMapping
    public RestApiResponse<HeartRateResDto> addHeartRateInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestBody HeartRateInsertDto heartRateInsertDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        heartRateInsertDto.setUserNo(userNo);
        return new RestApiResponse<>("심박수 추가 성공", heartRateService.addHeartRateInfo(heartRateInsertDto));
    }

    @Operation(summary = "심박수 삭제", description = "회원의 심박수를 삭제한다", tags = {"HeartRate"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "심박수 데이터 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HeartRateResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @Parameter(name = "no", description = "심박수 식별 번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Integer> deleteHeartRateInfo(@RequestHeader(required = false) String Authorization, @RequestParam Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        heartRateService.deleteHeartRateInfo(no, userNo);
        return new RestApiResponse<>("심박수 데이터 삭제 성공", no);
    }
}
