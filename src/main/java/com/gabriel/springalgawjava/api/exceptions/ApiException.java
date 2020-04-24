package com.gabriel.springalgawjava.api.exceptions;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ApiException {
    private Integer statusCode;
    private OffsetDateTime timestamp;
    private String message;
    private List<ApiField> fields;
}