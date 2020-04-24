package com.gabriel.springalgawjava.api.models;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentModel {
    private Integer id;
    private String content;
    private OffsetDateTime createdAt;
}