package com.secui.healthone.domain.calorie.api;

import com.secui.healthone.domain.calorie.dto.CalorieResDto;
import com.secui.healthone.domain.calorie.service.CalorieService;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calorie")
@RequiredArgsConstructor
@Tag(name = "Calorie", description = "칼로리 관련 컨트롤러")
public class CalorieController {

    private final CalorieService calorieService;

    @Operation(summary = "해당 날짜 섭취, 소비 칼로리 조회", description = "해당 날짜 섭취, 소비 칼로리 조회 API", tags = {"Calorie"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "date날짜 섭취, 소모 칼로리 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CalorieResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RestApiResponse<CalorieResDto> getMeal(
            @Parameter(name = "date", description = "조회 날짜", example = "2023-05-03T00:00:00") @RequestParam("date") String date) {
        Integer userNo = 1;
        String mode = "day";
        return new RestApiResponse<>(date + "날짜 섭취, 소모 칼로리 조회 성공", calorieService.getCalorie(date, userNo, mode));
    }

    @Operation(summary = "해당 날짜로부터 일주일간 섭취, 소비 칼로리 조회", description = "해당 날짜로부터 일주일간 섭취, 소비 칼로리 조회 API", tags = {"Calorie"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "date날짜로부터 일주일 섭취, 소모 칼로리 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CalorieResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/week")
    public RestApiResponse<CalorieResDto> getMealWeekData(
            @Parameter(name = "date", description = "조회 날짜", example = "2023-05-03T00:00:00") @RequestParam("date") String date) {
        Integer userNo = 1;
        String mode = "week";
        return new RestApiResponse<>(date + "날짜로부터 일주일 섭취, 소모 칼로리 조회 성공", calorieService.getCalorie(date, userNo, mode));
    }
}
