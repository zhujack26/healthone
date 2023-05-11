package com.secui.healthone.global;

import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.response.RestApiResponse;
import com.secui.healthone.global.error.errorcode.CustomErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "Test", description = "Challenge 서버 테스트 관련 컨트롤러")
public class TestController {

    @Operation(summary = "서버 응답", description = "서버응답 API", tags = {"Test"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Challenge Server is running", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }) })
    @GetMapping
    public RestApiResponse<String> testServer(){
        return new RestApiResponse<>("Challenge Server is running", null);
    }

    @Operation(summary = "CI/CD 테스트용 서버 응답", description = "서버응답 API", tags = {"Test"})
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Recommend Server is running", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),
            @Content(mediaType = "*/*", schema = @Schema(implementation = RestApiResponse.class)) }) })
    @GetMapping("/cicd")
    public RestApiResponse<String> cicdTestServer(){
        return new RestApiResponse<>("Recommend Server is running", null);
    }

    @Operation(summary = "서버 에러 응답", description = "서버 에러 응답 API", tags = {"Test"})
    @GetMapping("/error")
    public ResponseEntity<String> testError(){
        throw new RuntimeException("RUNTIME ERROR");
    }

    @Operation(summary = "서버 커스텀 에러 응답", description = "서버 커스텀 에러 응답 API", tags = {"Test"})
    @GetMapping("/customerror")
    public RestApiException testCustomError(){
        throw new RestApiException(CustomErrorCode.USER_401);
    }

}


