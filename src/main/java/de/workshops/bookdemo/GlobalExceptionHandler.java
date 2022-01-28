package de.workshops.bookdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerErrorDto> handleException(Exception ex) {
        ServerErrorDto dto = ServerErrorDto.builder()
            .errorMessage(ex.getMessage())
            .errorCode(42)
            .build();

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(dto);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServerErrorDto {
        private String errorMessage;
        private Integer errorCode;
        private String errorParam;
    }

}
