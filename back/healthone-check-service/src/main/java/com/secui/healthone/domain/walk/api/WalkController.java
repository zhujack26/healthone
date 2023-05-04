package com.secui.healthone.domain.walk.api;

import com.secui.healthone.domain.walk.dto.WalkReqDto;
import com.secui.healthone.domain.walk.dto.WalkResDto;
import com.secui.healthone.domain.walk.service.WalkService;
import com.secui.healthone.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/walk")
@Slf4j
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    @GetMapping
    public RestApiResponse<List<WalkResDto>> getWalkEntitiesForSevenDays(@RequestParam String dateTime) {
        return new RestApiResponse<>("걸음 수 리스트 조회 성공" , walkService.getWalkEntitiesForSevenDays(dateTime));
    }

    @GetMapping("/detail")
    public RestApiResponse<List<WalkResDto>> getDetailedWalkInfo(@RequestParam String date) {
        return new RestApiResponse<>("걸음 수 세부 정보 조회 성공" , walkService.getDetailedWalkInfo(date));
    }

    @PostMapping
    public RestApiResponse<?> insertWalkInfo(@RequestBody WalkReqDto walkReqDto) {
        return new RestApiResponse<>("걸음 수 등록 성공" , walkService.insertWalk(walkReqDto));
    }

    @DeleteMapping
    public RestApiResponse<?> deleteWalkInfo(@RequestParam("no") Integer no) {
        walkService.deleteWalk(no);
        return new RestApiResponse<>("걸음 수 삭제 성공", null);
    }

}
