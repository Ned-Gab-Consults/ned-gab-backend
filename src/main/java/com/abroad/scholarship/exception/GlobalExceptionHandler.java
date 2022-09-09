package com.abroad.scholarship.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> handleBadCredentialsEx(AlreadyExistException ex, WebRequest request){
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDetail> handleNotAssocaitatedWithStationEx(ResourceNotFoundException ex, WebRequest request){
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetail);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiErrorDetail> handleFileNotFoundEx(FileNotFoundException ex, WebRequest request){
        ApiErrorDetail errorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetail);
    }

}

