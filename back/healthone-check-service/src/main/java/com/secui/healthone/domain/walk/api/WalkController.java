package com.secui.healthone.domain.walk.api;

import com.secui.healthone.domain.walk.dto.WalkReqDto;
import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.domain.walk.service.WalkService;
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
@RequestMapping("/api/walk")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Walk", description = "걸음 수 관련 컨트롤러")
public class WalkController {

    private final WalkService walkService;
    private final TokenService tokenService;

    @Operation(summary = "걸음 수 리스트 조회", description = "걸음 수 리스트 조회 API", tags = {"Walk"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "걸음 수 리스트 조회 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WalkResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @Parameter(name = "dateTime", description = "걸음수 리스트 조회 날짜", example = "2023-05-03")
    @GetMapping
    public RestApiResponse<List<WalkResDto>> getWalkEntitiesForSevenDays(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("dateTime") String dateTime) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("걸음 수 리스트 조회 성공" , walkService.getWalkEntitiesForSevenDays(dateTime, userNo));
    }

    @Operation(summary = "걸음 수 세부 정보 조회", description = "걸음 수 세부 정보 조회 API", tags = {"Walk"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "걸음 수 세부 정보 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = WalkResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }),
            @ApiResponse(responseCode = "DB_100", description = "DB에 해당 데이터를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),  })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @Parameter(name = "date", description = "걸음수 날짜", example = "2023-05-03")
    @GetMapping("/detail")
    public RestApiResponse<List<WalkResDto>> getDetailedWalkInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestParam String date) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("걸음 수 세부 정보 조회 성공" , walkService.getDetailedWalkInfo(date, userNo));
    }

    @Operation(summary = "걸음 수 등록", description = "걸음 수 등록 API", tags = {"Walk"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "걸음 수 데이터 등록 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WalkResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "걸음 수 등록 객체")
    @PostMapping
    public RestApiResponse<?> insertWalkInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestBody WalkReqDto walkReqDto) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        walkReqDto.setUserNo(userNo);
        return new RestApiResponse<>("걸음 수 등록 성공" , walkService.insertWalk(walkReqDto));
    }

    @Operation(summary = "걸음 수 데이터 삭제", description = "걸음 수 데이터 삭제 API", tags = {"Walk"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "걸음 수 데이터 삭제 성공", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WalkResDto.class))),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class))  }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", required = false, example = "Bearer access_token")
    @Parameter(name = "no", description = "걸음 수 식별번호", example = "1")
    @DeleteMapping
    public RestApiResponse<?> deleteWalkInfo(@RequestHeader(required = false) String Authorization, @Valid @RequestParam("no") Integer no) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        walkService.deleteWalk(no, userNo);
        return new RestApiResponse<>("걸음 수 삭제 성공", null);
    }

}
