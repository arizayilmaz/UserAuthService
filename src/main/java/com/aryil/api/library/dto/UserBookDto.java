package com.aryil.api.library.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserBookDto {
    private UUID id;
    private UUID bookId;
    private String title;
    private String author;
    private String type;
    private Instant addedAt;
}
