package com.cns.assignment.exception.handler;


import com.cns.assignment.exception.UnAuthorizedException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    @HttpConstraint
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, "Argument Not Valid", errors);

        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());

    }



    @ExceptionHandler({UnAuthorizedException.class})
    public ResponseEntity<?> handleUnauthorizedAction(UnAuthorizedException ex, WebRequest request){
        String error ="You ain't allowed to do this action";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> dataIntegrityVioalation(DataIntegrityViolationException ex, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage(), "Username or Email already exist");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> cosntrainsViolation(ConstraintViolationException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage(), "Data already Exist");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ PropertyValueException.class })
    public ResponseEntity<Object> properPartyValue(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ BadCredentialsException.class })
    public ResponseEntity<Object> handleBadCredential(BadCredentialsException ex, WebRequest request) {
        log.info(ex.getClass().toString());
        log.info(ex.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), "User Name Or Password is Not Valid");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        log.info(ex.getClass().toString());
log.info(ex.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

}
