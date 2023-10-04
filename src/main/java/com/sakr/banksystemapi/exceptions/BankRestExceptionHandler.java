package com.sakr.banksystemapi.exceptions;

import com.sakr.banksystemapi.exceptions.customexceptions.NotAuthToSeeResourseException;
import com.sakr.banksystemapi.exceptions.customexceptions.ResourceNotFoundException;
import com.sakr.banksystemapi.model.error.ResponseErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;


@ControllerAdvice
public class BankRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseErrorModel> handleResourceNotFoundException(ResourceNotFoundException exc) {

        ResponseErrorModel error =
                ResponseErrorModel.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(exc.getMessage())
                        .occuredAt(new Timestamp(System.currentTimeMillis()))
                        .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseErrorModel> handleNotAuthToSeeResourseException(NotAuthToSeeResourseException exc) {

        ResponseErrorModel error =
                ResponseErrorModel.builder()
                        .status(HttpStatus.NOT_ACCEPTABLE.value())
                        .message(exc.getMessage())
                        .occuredAt(new Timestamp(System.currentTimeMillis()))
                        .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ResponseErrorModel> handleGeneralExceptions(Exception exc) {
        ResponseErrorModel error =
                ResponseErrorModel.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(exc.getMessage())
                        .occuredAt(new Timestamp(System.currentTimeMillis()))
                        .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
