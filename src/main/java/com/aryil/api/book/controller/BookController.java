package com.aryil.api.book.controller;

import com.aryil.api.book.dto.BookCreateRequest;
import com.aryil.api.book.dto.BookDto;
import com.aryil.api.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookCreateRequest req) {
        return ResponseEntity.ok(bookService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> list() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }
}
