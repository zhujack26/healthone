package com.secui.healthone.domain.sport.api;

import com.secui.healthone.domain.sport.dto.SportResDto;
import com.secui.healthone.domain.sport.service.SportService;
import com.secui.healthone.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sport")
@RequiredArgsConstructor
@Tag(name = "Sport", description = "일반 운동 데이터 관련 컨트롤러")
public class SportController {

    private final SportService sportService;

    @Operation(summary = "일반 운동 데이터 검색 정보 반환", description = "일반 운동 데이터 검색 정보 반환 API", tags = {"Sport"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "운동 데이터 검색 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SportResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class))
            }),
    })
    @GetMapping("/search")
    public RestApiResponse<List<SportResDto>> searchSport (@RequestParam("name") String name) {
        return new RestApiResponse<>("운동 데이터 검색 성공", sportService.searchSport(name));
    }

    @Operation(summary = "운동 데이터 조회 반환", description = "일반 운동 데이터 조회 API", tags = {"Sport"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "운동 데이터 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SportResDto.class)),
                    @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class))
            }),
    })
    @GetMapping
    public RestApiResponse<SportResDto> getSportData (@RequestParam("no") Integer no) {
        return new RestApiResponse<>("운동 데이터 조회 성공", sportService.getSport(no));
    }
}
