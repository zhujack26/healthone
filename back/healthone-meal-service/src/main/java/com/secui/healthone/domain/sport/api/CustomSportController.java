package com.secui.healthone.domain.sport.api;

import com.secui.healthone.domain.sport.dto.CustomSportReqDto;
import com.secui.healthone.domain.sport.dto.CustomSportResDto;
import com.secui.healthone.domain.sport.service.CustomSportService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customsport")
@RequiredArgsConstructor
public class CustomSportController {
    private final CustomSportService customSportService;

    // 운동 데이터 검색
    @GetMapping("/search")
    public RestApiResponse<List<CustomSportResDto>> searchCustomSport (@RequestParam("name") String name) {
        Integer userNo = 1;
        return new RestApiResponse<>("사용자 운동 데이터 검색 성공", customSportService.searchCustomSport(userNo, name));
    }

    // 운동 데이터 단건 조회
    @GetMapping
    public RestApiResponse<CustomSportResDto> getCustomSportData (@RequestParam("no") Integer no) {
        Integer userNo = 1;
        return new RestApiResponse<>("사용자 운동 데이터 조회 성공", customSportService.getCustomSport(userNo, no));
    }

    @PostMapping
    public RestApiResponse<CustomSportResDto> insertCustomSportData (@RequestBody CustomSportReqDto reqDto) {
        return new RestApiResponse<>("사용자 운동 데이터 등록 성공", customSportService.insertCustomSport(reqDto));
    }

    @PatchMapping
    public RestApiResponse<CustomSportResDto> updateCustomSportData (@RequestBody CustomSportReqDto reqDto) {
        return new RestApiResponse<>("사용자 운동 데이터 수정 성공", customSportService.insertCustomSport(reqDto));
    }

    @DeleteMapping
    public RestApiResponse<Integer> deleteCustomSportData (@RequestBody CustomSportReqDto reqDto) {
        customSportService.deleteCustomSport(reqDto);
        return new RestApiResponse<>("사용자 운동 데이터 삭제 성공", reqDto.getNo());
    }
}
