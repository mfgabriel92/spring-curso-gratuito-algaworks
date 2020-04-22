package com.gabriel.springalgawjava.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gabriel.springalgawjava.domain.exceptions.DuplicatedResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException e = new ApiException();
        e.setStatusCode(status.value());
        e.setTimestamp(LocalDateTime.now());
        e.setMessage("One or more fields are invalid. Please check and try again.");
        e.setFields(getFieldsWithError(ex.getBindingResult()));

        return super.handleExceptionInternal(ex, e, headers, status, request);
    }

    @ExceptionHandler(DuplicatedResourceException.class)
    protected ResponseEntity<Object> handleDuplicatedResourceException(DuplicatedResourceException ex, WebRequest request) {
        ApiException e = new ApiException();
        e.setStatusCode(HttpStatus.BAD_REQUEST.value());
        e.setTimestamp(LocalDateTime.now());
        e.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, e, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<ApiField> getFieldsWithError(BindingResult result) {
        List<ApiField> fields = new ArrayList<>();

        for (ObjectError error : result.getAllErrors()) {
            String name = ((FieldError) error).getField();
            String reason = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new ApiField(name, reason));
        }

        return fields;
    }
}