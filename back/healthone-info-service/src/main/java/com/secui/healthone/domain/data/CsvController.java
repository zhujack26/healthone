package com.secui.healthone.domain.data;

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

    @Operation(summary = "회원 건강 데이터 다운", description = "회원 건강 데이터를 다운로드 한다", tags = {"check-data-download"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HealthInfo 데이터 다운로드 성공", content = {
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping(value = "/healthinfo", produces = "text/csv")
    public ResponseEntity<?> downloadWalkData(@RequestHeader(required = false) String Authorization, HttpServletResponse response) throws IOException {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        String exportFileName = "HealthInfo-" + LocalDateTime.now() + ".txt";
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        csvService.writeHealthInfoDtoToCsv(response, userNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 건강 기록 데이터 다운", description = "회원 건강 기록 다운로드 한다", tags = {"check-data-download"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HealthStat 데이터 다운로드 성공", content = {
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }), })
    @SecurityRequirement(name = "bearerAuth")
    @Parameter(name = "Authorization", description = "회원 Access Token", example = "Bearer access_token")
    @GetMapping(value = "/sleep", produces = "text/csv")
    public ResponseEntity<?> downloadHeartRateData(@RequestHeader(required = false) String Authorization, HttpServletResponse response) throws IOException {
        String accessToken = HeaderUtil.getAccessTokenString(Authorization);
        Integer userNo = tokenService.getUserNo(accessToken);
        String exportFileName = "HealthStat-" + LocalDateTime.now() + ".txt";
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + exportFileName);
        csvService.writeHealthStatDtoToCsv(response, userNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
