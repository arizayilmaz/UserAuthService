package com.aryil.api.book.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private String type;
    private Instant createdAt;
}
