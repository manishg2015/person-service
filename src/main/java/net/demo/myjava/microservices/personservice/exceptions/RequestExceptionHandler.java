package net.demo.myjava.microservices.personservice.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RequestExceptionHandler {

    public static final String STRING_NOT_FOUND = "%s - %s NOT_FOUND";
    /**
     * No match found handler.
     *
     * @param request the request
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> exceptionHandler(ServerHttpRequest request, Exception exception) {



        log.info("*******Exception occurred:", exception.getClass());
        log.error("Exception Trace:", exception);
        String path = request.getURI().getPath();
        //String message = "Exception while processing the request.";
        String message = exception.getMessage();
        ApiError errorResponse = ApiError.builder().message(message).requestUri(path).
                method(Objects.requireNonNull(request.getMethod()).name())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(String.valueOf(LocalDateTime.now(ZoneOffset.UTC))).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Get validation errors
     *
     * @param webExchangeBindException the error thrown
     * @return list of {@link ApiValidationError}
     */
    private List<ApiValidationError> getValidationErrors(WebExchangeBindException webExchangeBindException) {

        return webExchangeBindException.getFieldErrors()
                .stream()
                .map(er -> {
                    log.info("validation error on field: " + er.getField() + " value:" + er.getRejectedValue());
                    return new ApiValidationError(er.getField(), Objects.requireNonNullElse(er.getRejectedValue(), "").toString(),
                            er.getDefaultMessage());
                })
                .collect(Collectors.toList());
    }









}
