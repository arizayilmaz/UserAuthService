package com.aryil.api.library.mapper;

import com.aryil.api.library.dto.UserBookDto;
import com.aryil.api.library.entity.UserBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserBookMapper {
    @Mapping(source="book.id", target="bookId")
    @Mapping(source="book.title", target="title")
    @Mapping(source="book.author", target="author")
    @Mapping(source="book.type", target="type")
    UserBookDto toDto(UserBook ub);
}
