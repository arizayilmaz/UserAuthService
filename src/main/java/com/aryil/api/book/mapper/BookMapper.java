package com.aryil.api.book.mapper;

import com.aryil.api.book.dto.BookDto;
import com.aryil.api.book.dto.BookCreateRequest;
import com.aryil.api.book.entity.Book;
import com.aryil.api.book.entity.BookType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface BookMapper {

    default Book toEntity(BookCreateRequest req) {
        return Book.builder()
                .title(req.getTitle())
                .author(req.getAuthor())
                .type(BookType.valueOf(req.getType()))
                .createdAt(Instant.now())
                .build();
    }

    BookDto toDto(Book book);
}
