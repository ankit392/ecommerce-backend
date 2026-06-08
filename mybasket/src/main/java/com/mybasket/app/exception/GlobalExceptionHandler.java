package com.mybasket.app.exception;


import com.mybasket.app.dto.ErrorResponse;
import com.mybasket.app.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.ErrorManager;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementFound(NoSuchElementException ex){

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

//MethodArgumentNotValidException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

//        FieldError fieldError = ex.getBindingResult().getFieldError();
//     //   String field = fieldError.getField();
//        String defaultMessage = fieldError.getDefaultMessage();


        List<ValidationErrorResponse> list = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorResponse(fieldError.getField(),
                        fieldError.getDefaultMessage())

                ).toList();

        return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);


    }









}
