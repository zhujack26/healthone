package com.secui.healthone.domain.meal.api;

import com.secui.healthone.domain.meal.dto.MealReqDto;
import com.secui.healthone.domain.meal.dto.MealResDto;
import com.secui.healthone.domain.meal.service.MealService;
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
@RequestMapping("/api/meal-record")
@RequiredArgsConstructor
@Tag(name = "Meal", description = "식사 관련 컨트롤러")
public class MealController {

    private final MealService mealService;
    private final TokenService tokenService;

    @Operation(summary = "식사 정보 단일 조회", description = "식사 정보 단일 조회 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 정보 단일 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "식사 정보 식별번호", example = "1")
    @GetMapping
    public RestApiResponse<MealResDto> getMeal(@RequestHeader(required = false) String Authorization, @RequestParam(value = "no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("식사 정보 단일 조회 성공", mealService.getMeal(no, userNo));
    }

    @Operation(summary = "식사 정보 리스트 조회", description = "식사 정보 리스트 조회 (날짜기반) API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 리스트 조회 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MealResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "date", description = "조회 날짜", example = "2023-05-03T00:00:00")
    @GetMapping("/list")
    public RestApiResponse<List<MealResDto>> getMealList(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("date") String date) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>(date+ "식사 리스트 조회 성공", mealService.getMealList(date, userNo));
    }

    @Operation(summary = "식사 등록", description = "식사 등록 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 등록 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "식사 등록 객체")
    @PostMapping
    public RestApiResponse<MealResDto> insertMeal(@RequestHeader(required = false) String Authorization, @Valid @RequestBody MealReqDto requestDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        requestDto.setUserNo(userNo);
        return new RestApiResponse<>("식사 등록 성공", mealService.insertMeal(requestDto));
    }

    @Operation(summary = "식사 수정", description = "식사 수정 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "식사 수정 객체")
    @PatchMapping
    public RestApiResponse<MealResDto> updateMeal(@RequestHeader(required = false) String Authorization, @Valid @RequestBody MealReqDto requestDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        requestDto.setUserNo(userNo);
        return new RestApiResponse<>("식사 수정 성공", mealService.modifyMeal(requestDto));
    }

    @Operation(summary = "식사 삭제", description = "식사 삭제 API", tags = {"Meal"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "식사 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MealResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "식사 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<Integer> updateMeal(@RequestHeader(required = false) String Authorization, @RequestParam("no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        mealService.deleteMeal(no, userNo);
        return new RestApiResponse<>("식사 삭제 성공", no);
    }
}
