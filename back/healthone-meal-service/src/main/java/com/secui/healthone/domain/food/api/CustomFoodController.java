package com.secui.healthone.domain.food.api;

import com.secui.healthone.domain.food.dto.CustomFoodReqDto;
import com.secui.healthone.domain.food.dto.CustomFoodResDto;
import com.secui.healthone.domain.food.service.CustomFoodService;
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
@RequestMapping("/api/customfood")
@RequiredArgsConstructor
@Tag(name = "CustomFood", description = "사용자 음식 데이터 관련 컨트롤러")
public class CustomFoodController {

    private final CustomFoodService customFoodService;
    private final TokenService tokenService;

    @Operation(summary = "사용자 음식 단일 정보 반환", description = "사용자 음식 데이터 단건 조회 API", tags = {"CustomFood"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 음식 데이터 단건 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomFoodResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "사용자 음식 식별번호", example = "1")
    @GetMapping
    public RestApiResponse<CustomFoodResDto> getCustomFood(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("사용자 음식 데이터 단건 조회 성공", customFoodService.getCustomFood(no, userNo));
    }


    @Operation(summary = "사용자 음식 데이터 검색", description = "사용자 음식 데이터 검색 API", tags = {"CustomFood"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 음식 데이터 검색 성공", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomFoodResDto.class))),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class))  }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "name", description = "사용자 음식 이름", example = "김밥")
    @GetMapping("/serarch")
    public RestApiResponse<List<CustomFoodResDto>> getCustomFood(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("name") String name) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("사용자 음식 데이터 검색 성공", customFoodService.searchCustomFood(name, userNo));
    }

    @Operation(summary = "사용자 음식 데이터 등록", description = "사용자 음식 데이터 등록 API", tags = {"CustomFood"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 음식 데이터 등록 성공", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomFoodResDto.class))),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "사용자 음식 등록 객체")
    @PostMapping
    public RestApiResponse<CustomFoodResDto> getCustomFood(@RequestHeader(required = false) String Authorization, @Valid @RequestBody CustomFoodReqDto requestDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        requestDto.setUserNo(userNo);
        return new RestApiResponse<>("사용자 음식 데이터 등록 성공", customFoodService.saveCustomFood(requestDto));
    }

    @Operation(summary = "사용자 음식 데이터 수정", description = "사용자 음식 데이터 수정 API", tags = {"CustomFood"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 음식 데이터 수정 성공", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomFoodResDto.class))),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "식사 수정 객체")
    @PatchMapping
    public RestApiResponse<CustomFoodResDto> modifyCustomFood(@RequestHeader(required = false) String Authorization, @Valid @RequestBody CustomFoodReqDto requestDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        requestDto.setUserNo(userNo);
        return new RestApiResponse<>("사용자 음식 데이터 수정 성공", customFoodService.updateCustomFood(requestDto));
    }

    @Operation(summary = "사용자 음식 데이터 삭제", description = "사용자 음식 데이터 삭제 API", tags = {"CustomFood"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "사용자 음식 데이터 삭제 성공", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomFoodResDto.class))),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class))  }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @Parameter(name = "no", description = "사용자 음식 데이터 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<CustomFoodResDto> deleteCustomFood(@RequestHeader(required = false) String Authorization,@Valid @RequestParam Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        customFoodService.deleteCustomFood(no, userNo);
        return new RestApiResponse<>("사용자 음식 데이터 삭제 성공", null);
    }
}
