package com.gabriel.springalgawjava.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiField {
    private String name;
    private String reason;
}