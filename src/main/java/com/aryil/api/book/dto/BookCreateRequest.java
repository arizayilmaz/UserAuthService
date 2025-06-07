package com.aryil.api.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookCreateRequest {
    @NotBlank
    private String title;
    private String author;

    @NotNull
    private String type;  // "BOOK" veya "MANGA"
}
