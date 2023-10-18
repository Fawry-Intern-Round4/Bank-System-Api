package com.sakr.banksystemapi.exceptions;

import com.sakr.banksystemapi.exceptions.customexceptions.NotAuthToSeeResourceException;
import com.sakr.banksystemapi.mapper.ErrorMapper;
import com.sakr.banksystemapi.model.ResponseModel;
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
    public ResponseEntity<ResponseModel> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage()))
                        .build()
                );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseModel> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()))
                        .build()
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseModel> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()))
                        .build()
                );

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseModel> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()))
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList().toString().replace(",", " And ");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.BAD_REQUEST, message))
                        .build()
                );
    }

    @ExceptionHandler(NotAuthToSeeResourceException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ResponseModel> handleNotAuthToSeeResourceException(NotAuthToSeeResourceException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()))
                        .build()
                );
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ResponseModel> handleGeneralExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(ResponseModel
                        .builder()
                        .success(false)
                        .error(errorMapper.toErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getMessage()))
                        .build()
                );
    }

}
