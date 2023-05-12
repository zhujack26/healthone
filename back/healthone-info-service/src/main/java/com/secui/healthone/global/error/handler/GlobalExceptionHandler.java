package com.secui.healthone.global.error.handler;

import com.secui.healthone.global.error.errorcode.CommonErrorCode;
import com.secui.healthone.global.error.errorcode.ErrorCode;
import com.secui.healthone.global.error.exception.RestApiException;
import com.secui.healthone.global.error.response.ErrorResponse;
import com.secui.healthone.global.mattermost.util.NotificationManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 자바 코드는 Spring Framework에서 제공하는 @RestControllerAdvice 어노테이션을 이용하여 전역 예외 처리를 담당하는 GlobalExceptionHandler 클래스입니다.
 * 이 클래스는 ResponseEntityExceptionHandler 클래스를 상속받아 구현되었습니다.
 * ResponseEntityExceptionHandler 클래스는 Spring Framework에서 제공하는 예외 처리 클래스로, 다양한 예외에 대한 기본 처리를 제공합니다. 이 클래스를 상속받으면 기본 예외 처리 이외에 추가적인 예외 처리를 정의할 수 있습니다.
 * 해당 클래스에서는 @ExceptionHandler 어노테이션을 이용하여 예외 처리 메서드를 정의하고 있습니다.
 * `@ExceptionHandler` 어노테이션은 해당 메서드가 어떤 예외를 처리하는지 명시해줍니다.
 *
 * handleQuizException 메서드는 RestApiException 예외를 처리합니다. 해당 예외의 ErrorCode를 가져와 handleExceptionInternal 메서드를 호출합니다.
 * handleIllegalArgument 메서드는 IllegalArgumentException 예외를 처리합니다. 로그를 남기고 CommonErrorCode.INVALID_PARAMETER 에러 코드를 사용하여 handleExceptionInternal 메서드를 호출합니다.
 * handleMethodArgumentNotValid 메서드는 MethodArgumentNotValidException 예외를 처리합니다. 로그를 남기고 CommonErrorCode.INVALID_PARAMETER 에러 코드를 사용하여 handleExceptionInternal 메서드를 호출합니다.
 * handleAllException 메서드는 모든 예외를 처리합니다. 로그를 남기고 CommonErrorCode.INTERNAL_SERVER_ERROR 에러 코드를 사용하여 handleExceptionInternal 메서드를 호출합니다.
 * handleExceptionInternal 메서드는 ErrorCode를 이용하여 ErrorResponse 객체를 생성하고 이를 ResponseEntity에 담아 반환합니다. makeErrorResponse 메서드는 ErrorCode와 필요한 경우 메시지를 이용하여 ErrorResponse 객체를 생성합니다.
 */

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final NotificationManager notificationManager;

    private String getParams(HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append("/n");
        }
        return params.toString();
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleQuizException(final RestApiException e, HttpServletRequest req) {
        log.warn("CA : RestApiException", e);
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        final ErrorCode errorCode = e.getErrorCode();
        return handleExceptionInternal(errorCode, req);
//        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleIllegalArgument(final RuntimeException e, HttpServletRequest req) {
        log.warn("CA : RuntimeException", e);
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, req);
//        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(final IllegalArgumentException e, HttpServletRequest req) {
        log.warn("CA : IllegalArgumentException", e);
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, req);
//        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException e,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        log.warn("handleIllegalArgument", e);
        final ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(e, errorCode);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(final Exception e, HttpServletRequest req) {
        log.warn("CA : Exception", e);
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        final ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode, req);
    }

    private ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode, HttpServletRequest req) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }
    private ErrorResponse makeErrorResponse(final ErrorCode errorCode) {
        return ErrorResponse.builder()
                .error(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    private ResponseEntity<Object> handleExceptionInternal(final ErrorCode errorCode, final String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }
    private ErrorResponse makeErrorResponse(final ErrorCode errorCode, final String message) {
        return ErrorResponse.builder()
                .error(errorCode.name())
                .message(message)
                .build();
    }

    private ResponseEntity<Object> handleExceptionInternal(final BindException e, final ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }
    private ErrorResponse makeErrorResponse(final BindException e, final ErrorCode errorCode) {
        final List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .message("필드 유효성 검증 실패")
                .errors(validationErrorList)
                .error("VALIDATION_ERROR")
                .build();
    }
}