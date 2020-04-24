package com.gabriel.springalgawjava.api.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.gabriel.springalgawjava.domain.models.ServiceOrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceOrderModel {
    private Integer id;
    private ClientModel client;
    private String description;
    private BigDecimal price;
    private ServiceOrderStatus status;
    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;
}