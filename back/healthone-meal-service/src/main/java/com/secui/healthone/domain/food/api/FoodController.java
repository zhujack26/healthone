package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.FoodResponseDto;
import com.secui.healthone.domain.food.service.FoodService;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
@Tag(name = "Food", description = "음식 데이터 관련 컨트롤러")
public class FoodController {
    private final FoodService foodService;

    @Operation(summary = "일반 음식 단일 정보 반환", description = "일반 음식 단일 정보 반환 API", tags = {"Food"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "음식 데이터 단일 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FoodResponseDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))), })
    @Parameter(name = "no", description = "음식 단일 데이터 식별번호", example = "1")
    @GetMapping
    public RestApiResponse<FoodResponseDto> getFood(@RequestParam("no") Integer no) {
        return new RestApiResponse<>("음식 데이터 단일 조회 성공", foodService.getFood(no));
    }

    @Operation(summary = "일반 음식 검색 정보 반환", description = "일반 음식 검색 정보 반환 API", tags = {"Food"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "음식 검색 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FoodResponseDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @Parameter(name = "name", description = "음식 이름", example = "김밥")
    @Parameter(in = ParameterIn.QUERY, description = "페이지 번호 (0~N)", name = "page", content = @Content(schema = @Schema(type = "Integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY, description = "페이지 크기 (기본: 20)", name = "size", content = @Content(schema = @Schema(type = "Integer", defaultValue = "20")))
    @GetMapping("/search")
    public RestApiResponse<Slice<FoodResponseDto>> searchFood(@RequestParam("name") String name, @ParameterObject Pageable pageable) {
        return new RestApiResponse<>("음식 검색 조회 성공", foodService.searchFood(name, pageable));
    }

}
