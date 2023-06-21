package com.cybersoft.cozastore_java21.exception;

import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCustomException {
    //Noi dang ky cac Exception se kich hoat code ben trong ham
    @ExceptionHandler(CustomFileNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setData(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setData(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
