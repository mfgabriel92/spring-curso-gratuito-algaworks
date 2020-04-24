package com.gabriel.springalgawjava.api.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceOrderRequest {
    @NotNull
    private Integer clientId;
    
    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;
}