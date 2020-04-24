package com.gabriel.springalgawjava.domain.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "service_orders")
@EqualsAndHashCode(of = "id")
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Client client;

    private String description;
    private BigDecimal price;
    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;

    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus status;

    @OneToMany(mappedBy = "serviceOrder")
    private List<Comment> comments = new ArrayList<>();
}