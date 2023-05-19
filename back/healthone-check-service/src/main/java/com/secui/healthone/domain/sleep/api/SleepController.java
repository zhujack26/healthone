package com.secui.healthone.domain.sleep.api;

import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.sleep.dto.SleepInsertDto;
import com.secui.healthone.domain.sleep.dto.SleepResDto;
import com.secui.healthone.domain.sleep.dto.SleepUpdateDto;
import com.secui.healthone.domain.sleep.service.SleepService;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.response.RestApiResponse;
import com.secui.healthone.global.util.HeaderUtil;
import com.secui.healthone.global.util.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import java.util.List;

@RestController
@RequestMapping("/api/sleep")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Sleep", description = "수면 관련 컨트롤러")
public class SleepController {
    private final SleepService sleepService;
    private final TokenService tokenService;

//    @Operation(summary = "수면정보 리스트 조회", description = "회원의 수면정보를 최신순으로 출력한다", tags = {"Sleep"})
//    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = {
//            @Content(mediaType = "application/json", schema = @Schema(implementation = HeartRateResDto.class)),
//            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
//    @Parameter(in = ParameterIn.QUERY, description = "페이지 번호 (0..N)", name = "page", example = "0",
//            content = @Content(schema = @Schema(type = "Integer", defaultValue = "0")))
//    @Parameter(in = ParameterIn.QUERY, description = "페이지 크기", name = "size", example = "7",
//            content = @Content(schema = @Schema(type = "Integer", defaultValue = "7")))
//    @SecurityRequirement(name = "bearerAuth")
//    @GetMapping("/list")
//    public RestApiResponse<Slice<SleepResDto>> getSleepDataList(@ParameterObject Pageable pageable) {
//        Integer userNo = 1;
//        return new RestApiResponse<>(pageable.getPageNumber()+"페이지 수면정보 리스트 조회 완료", sleepService.getSleepDataList(userNo, pageable));
//    }

    @Operation(summary = "수면정보 리스트 조회", description = "회원의 수면정보를 최신순 조회 API (이날로부터 일주일 데이터 반환)", tags = {"Sleep"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HeartRateResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "date", description = "수면 리스트 정보 날짜 조회(이날로부터 일주일 데이터 반환)", example = "2023-05-10T00:00:00")
    @GetMapping("/list")
    public RestApiResponse<List<SleepResDto>> getSleepDataList(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("date") String date) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>(date + "날짜부터 수면정보 리스트 조회 완료", sleepService.getSleepDataList(userNo, date));
    }

    @Operation(summary = "수면 세부 정보 조회", description = "수면 세부 정보 조회 조회 API (당일)", tags = {"Sleep"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "수면 세부 정보 조회 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SleepResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "date", description = "수면 세부 정보 조회 날짜", example = "2023-05-03T00:00:00")
    @GetMapping("/detail")
    public RestApiResponse<List<SleepResDto>> getSleepData(@RequestHeader(required = false) String Authorization, @Valid @RequestParam String date) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        List<SleepResDto> sleepList = sleepService.getSleepData(date, userNo);
        return new RestApiResponse<>("수면 세부 정보 조회 성공" , sleepList);
    }

    @Operation(summary = "수면 정보 등록", description = "수면 정보 등록 조회 API", tags = {"Sleep"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "수면 정보 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SleepResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수면 정보 등록 객체")
    @PostMapping
    public RestApiResponse<SleepResDto> addSleepInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestBody SleepInsertDto sleepInsertDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        sleepInsertDto.setUserNo(userNo);
        return new RestApiResponse<>("수면 정보 등록 성공" , sleepService.addSleepInfo(sleepInsertDto));
    }

    @Operation(summary = "수면 정보 수정", description = "수면 정보 수정 API", tags = {"Sleep"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "수면 정보 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SleepResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수면 정보 등록 객체")
    @PatchMapping
    public RestApiResponse<SleepResDto> updateSleepInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestBody SleepUpdateDto sleepUpdateDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        sleepUpdateDto.setUserNo(userNo);
        return new RestApiResponse<>("수면 정보 수정 성공" , sleepService.updateSleepInfo(sleepUpdateDto));
    }

    @Operation(summary = "수면 정보 삭제", description = "수면 정보 삭제 API", tags = {"Sleep"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "수면 정보 삭제 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SleepResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class))  }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "수면 데이터 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Integer> deleteSleepInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        sleepService.deleteSleepInfo(no, userNo);
        return new RestApiResponse<>("수면 정보 삭제 성공" , no);
    }
}
