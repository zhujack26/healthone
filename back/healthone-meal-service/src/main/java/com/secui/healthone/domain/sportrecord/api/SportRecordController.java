package com.secui.healthone.domain.sportrecord.api;

import com.secui.healthone.domain.sportrecord.dto.SportRecordReqDto;
import com.secui.healthone.domain.sportrecord.dto.SportRecordResDto;
import com.secui.healthone.domain.sportrecord.service.SportRecordService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sportrecord")
@RequiredArgsConstructor
@Tag(name = "SportRecord", description = "운동 기록 관련 컨트롤러")
public class SportRecordController {
    private final SportRecordService sportRecordService;
    private final TokenService tokenService;

    @Operation(summary = "날짜 운동 기록 정보 리스트 조회", description = "날짜 운동 기록 정보 리스트 조회 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "(날짜)날짜 운동 기록 정보 리스트 조회 성공", content = {
            @Content(mediaType = "application/json",  array = @ArraySchema(schema = @Schema(implementation =  SportRecordResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "date", description = "운동 날짜", example = "2023-05-03T00:00:00")
    @GetMapping
    public RestApiResponse<List<SportRecordResDto>> getSportRecordList(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("date") String date) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>(date +"날짜 운동 기록 정보 리스트 조회 성공", sportRecordService.getSportRecordList(date, userNo));
    }

    @Operation(summary = "운동 기록 등록", description = "운동 기록 등록 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "운동 기록 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SportRecordResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "운동 기록 등록 객체")
    @PostMapping
    public RestApiResponse<SportRecordResDto> insertSportRecord(@RequestHeader(required = false) String Authorization, @Valid @RequestBody SportRecordReqDto reqDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        reqDto.setUserNo(userNo);
        return new RestApiResponse<>("운동 기록 등록 성공", sportRecordService.insertSportRecord(reqDto));
    }

    @Operation(summary = "운동 기록 수정", description = "운동 기록 수정 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "운동 기록 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SportRecordResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "운동 기록 수정 객체")
    @PatchMapping
    public RestApiResponse<SportRecordResDto> updateSportRecord(@RequestHeader(required = false) String Authorization, @Valid @RequestBody SportRecordReqDto reqDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        reqDto.setUserNo(userNo);
        return new RestApiResponse<>("운동 기록 수정 성공", sportRecordService.updateSportRecord(reqDto));
    }

    @Operation(summary = "운동 기록 삭제", description = "운동 기록 삭제 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "운동 기록 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SportRecordResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "운동 기록 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Integer> deleteSportRecord(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        sportRecordService.deleteSportRecord(no, userNo);
        return new RestApiResponse<>("운동 기록 삭제 성공", no);
    }
}
