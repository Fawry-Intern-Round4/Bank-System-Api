package com.sakr.banksystemapi.exceptions;

import com.sakr.banksystemapi.exceptions.customexceptions.NotAuthToSeeResourceException;
import com.sakr.banksystemapi.mapper.ErrorMapper;
import com.sakr.banksystemapi.model.error.ResponseErrorModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@RequiredArgsConstructor
public class BankRestExceptionHandler {

    private final ErrorMapper errorMapper;

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ResponseErrorModel> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage()),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseErrorModel> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseErrorModel> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseErrorModel> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList().toString().replace(",", " And ");

        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.BAD_REQUEST, message),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotAuthToSeeResourceException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ResponseErrorModel> handleNotAuthToSeeResourceException(NotAuthToSeeResourceException ex) {

        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ResponseErrorModel> handleGeneralExceptions(Exception ex) {
        return new ResponseEntity<>(
                errorMapper.toErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage()),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

}
