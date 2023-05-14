package com.secui.healthone.domain.activity.api;

import com.secui.healthone.domain.activity.service.ActivityService;
import com.secui.healthone.domain.calorie.dto.CalorieResDto;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.response.RestApiResponse;
import com.secui.healthone.global.util.HeaderUtil;
import com.secui.healthone.global.util.TokenService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
@Tag(name = "Activity", description = "활동 관련 컨트롤러")
public class ActivityController {

    private final ActivityService activityService;
    private final TokenService tokenService;

    @Operation(summary = "회원 활동시간(최고, 총), 소비 칼로리(최고, 총) 반환 조회", description = "회원 활동시간, 칼로리 반환 API", tags = {"Activity"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "회원 활동량(총, 최고), 소비칼로리(총, 최고) 반환 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CalorieResDto.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) })})
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping
    public RestApiResponse<?> getActivityData(@RequestHeader(required = false) String Authorization) {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        return new RestApiResponse<>("회원 활동량(총, 최고), 소비칼로리(총, 최고) 반환 성공",activityService.getActivityData(userNo));
    }


}
