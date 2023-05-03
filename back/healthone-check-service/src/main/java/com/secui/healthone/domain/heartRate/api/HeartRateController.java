package com.secui.healthone.domain.heartRate.api;

import com.secui.healthone.domain.heartRate.dto.AddHeartRateInfoReqDto;
import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.heartRate.service.HeartRateService;
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
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heart-rate")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "HeartRate", description = "심박수 관련 컨트롤러")
public class HeartRateController {
    private final HeartRateService heartRateService;

    @Operation(summary = "심박수 리스트 조회", description = "회원의 심박수를 최신순으로 출력한다", tags = {"HeartRate"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HeartRateResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @Parameter(in = ParameterIn.QUERY, description = "페이지 번호 (0..N)", name = "page", example = "0",
            content = @Content(schema = @Schema(type = "Integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY, description = "페이지 크기", name = "size", example = "7",
            content = @Content(schema = @Schema(type = "Integer", defaultValue = "7")))
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RestApiResponse<Slice<HeartRateResDto>> getHeartRateInfo(@ParameterObject Pageable pageable) {
        Integer userNo = 1;
        return new RestApiResponse<>("", heartRateService.getHeartRateList(userNo, pageable));
    }

//    @GetMapping
//    public RestApiResponse<List<HeartRateResDto>> getWeeklyHeartRate(@RequestParam String dateTime) {
//        return new RestApiResponse<>(dateTime + "날짜 심박수 리스트 조회 성공", heartRateService.getWeeklyHeartRate(dateTime));
//    }

    @PostMapping
    public RestApiResponse<HeartRateResDto> addHeartRateInfo(@RequestBody AddHeartRateInfoReqDto addHeartRateInfoReqDto) {
        return new RestApiResponse<>("심박수 추가 성공", heartRateService.addHeartRateInfo(addHeartRateInfoReqDto));
    }

    @DeleteMapping
    public RestApiResponse<Void> deleteHeartRateInfo(@RequestParam Integer no) {
        heartRateService.deleteHeartRateInfo(no);
        return new RestApiResponse<>("심박수 데이터 삭제 성공", null);
    }
}
