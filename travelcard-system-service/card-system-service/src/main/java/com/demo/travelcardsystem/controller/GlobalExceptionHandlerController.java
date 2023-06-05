package com.demo.travelcardsystem.controller;

import com.demo.travelcardsystem.exception.InvalidCardException;
import com.demo.travelcardsystem.exception.InvalidDataProvidedException;
import com.demo.travelcardsystem.exception.InvalidRechargeAmount;
import com.demo.travelcardsystem.exception.TravelCardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler({InvalidCardException.class, InvalidRechargeAmount.class})
    public <T> ResponseEntity<T> handleInvalidRequestException(TravelCardException invalidCardException) {
        ResponseEntity<T> responseEntity = new ResponseEntity(invalidCardException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        invalidCardException.printStackTrace();
        return responseEntity;
    }

    @ExceptionHandler(InvalidDataProvidedException.class)
    public <T> ResponseEntity<T> handleInvalidDataProvidedException(InvalidDataProvidedException invalidDataProvidedException) {
        ResponseEntity<T> responseEntity = new ResponseEntity("Invalid request! Please check input", HttpStatus.BAD_REQUEST);
        invalidDataProvidedException.printStackTrace();
        return responseEntity;
    }

}
