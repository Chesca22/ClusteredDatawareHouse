package com.francisca.ClusteredDataWareHouse.controller;

import com.francisca.ClusteredDataWareHouse.dto.ExceptionResponse;
import com.francisca.ClusteredDataWareHouse.exception.DealAlreadyExistException;
import com.francisca.ClusteredDataWareHouse.exception.DealNotFoundException;
import com.francisca.ClusteredDataWareHouse.exception.ExceptionResponseService;
import com.francisca.ClusteredDataWareHouse.exception.InvalidCurrencyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {
    private final ExceptionResponseService<ExceptionResponse> responseService;

    @org.springframework.web.bind.annotation.ExceptionHandler(DealAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(DealAlreadyExistException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.CONFLICT), HttpStatus.CONFLICT);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(InvalidCurrencyException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DealNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(DealNotFoundException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
