package com.secui.healthone.domain.data;

import com.secui.healthone.domain.heartRate.dto.HeartRateResDto;
import com.secui.healthone.domain.walk.service.WalkService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/check-data-download")
@Tag(name = "check-data-download", description = "Check service 정보 다운로드 컨트롤러")
public class CsvController {

    private final CsvService csvService;
    private final TokenService tokenService;

    @Operation(summary = "걸음 수 데이터 다운", description = "걸음 수 데이터를 다운로드 한다", tags = {"check-data-download"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Walk 데이터 다운로드 성공", content = {
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping(value = "/walk", produces = "text/csv")
    public ResponseEntity<?> downloadWalkData(@RequestHeader(required = false) String Authorization, HttpServletResponse response) throws IOException {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        String exportFileName = "walk-" + LocalDateTime.now() + ".txt";
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        csvService.writeWalkDtoToCsv(response, userNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "수면 데이터 다운", description = "수면 데이터를 다운로드 한다", tags = {"check-data-download"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Sleep 데이터 다운로드 성공", content = {
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping(value = "/sleep", produces = "text/csv")
    public ResponseEntity<?> downloadHeartRateData(@RequestHeader(required = false) String Authorization, HttpServletResponse response) throws IOException {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        String exportFileName = "Sleep-" + LocalDateTime.now() + ".txt";
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        csvService.writeHeartRateDtoToCsv(response, userNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "심박수 데이터 다운", description = "심박수 데이터를 다운로드 한다", tags = {"check-data-download"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HeartRate 데이터 다운로드 성공", content = {
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping(value = "/heart-rate", produces = "text/csv")
    public ResponseEntity<?> downloadSleepData(@RequestHeader(required = false) String Authorization, HttpServletResponse response) throws IOException {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        String exportFileName = "HeartRate-" + LocalDateTime.now() + ".txt";
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        csvService.writeSleepDtoToCsv(response, userNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
