package com.aryil.api.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class AddBookToLibraryRequest {
    @NotNull
    private UUID bookId;
}
