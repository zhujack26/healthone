package com.secui.healthone.domain.sportrecord.api;

import com.secui.healthone.domain.sportrecord.dto.SportRecordReqDto;
import com.secui.healthone.domain.sportrecord.dto.SportRecordResDto;
import com.secui.healthone.domain.sportrecord.service.SportRecordService;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/api/sportrecord")
@RequiredArgsConstructor
@Tag(name = "SportRecord", description = "운동 기록 관련 컨트롤러")
public class SportRecordController {
    private final SportRecordService sportRecordService;

    @Operation(summary = "날짜 운동 기록 정보 리스트 조회", description = "날짜 운동 기록 정보 리스트 조회 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "(날짜)날짜 운동 기록 정보 리스트 조회 성공", content = {
            @Content(mediaType = "application/json",  array = @ArraySchema(schema = @Schema(implementation =  SportRecordResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RestApiResponse<List<SportRecordResDto>> getSportRecordList(@RequestParam("date") String date) {
        Integer userNo = 1;
        return new RestApiResponse<>(date +"날짜 운동 기록 정보 리스트 조회 성공", sportRecordService.getSportRecordList(date, userNo));
    }

    @Operation(summary = "운동 기록 등록", description = "운동 기록 등록 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SportRecordResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public RestApiResponse<SportRecordResDto> insertSportRecord(@RequestBody SportRecordReqDto reqDto) {
        return new RestApiResponse<>("운동 기록 등록 성공", sportRecordService.insertSportRecord(reqDto));
    }

    @Operation(summary = "운동 기록 수정", description = "운동 기록 수정 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "운동 기록 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SportRecordResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping
    public RestApiResponse<SportRecordResDto> updateSportRecord(@RequestBody SportRecordReqDto reqDto) {
        return new RestApiResponse<>("운동 기록 수정 성공", sportRecordService.insertSportRecord(reqDto));
    }

    @Operation(summary = "운동 기록 삭제", description = "운동 기록 삭제 API", tags = {"SportRecord"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "운동 기록 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SportRecordResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public RestApiResponse<Void> deleteSportRecord(@RequestParam("no") Integer no) {
        sportRecordService.deleteSportRecord(no);
        return new RestApiResponse<>("운동 기록 삭제 성공", null);
    }


}
