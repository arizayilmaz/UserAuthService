package com.aryil.api.book.service;

import com.aryil.api.book.dto.BookCreateRequest;
import com.aryil.api.book.dto.BookDto;
import com.aryil.api.book.entity.Book;
import com.aryil.api.book.mapper.BookMapper;
import com.aryil.api.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto create(BookCreateRequest req) {
        Book saved = bookRepository.save(bookMapper.toEntity(req));
        return bookMapper.toDto(saved);
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto findById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
