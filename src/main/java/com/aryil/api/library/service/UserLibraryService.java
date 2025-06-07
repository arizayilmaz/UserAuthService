package com.aryil.api.library.service;

import com.aryil.api.book.entity.Book;
import com.aryil.api.library.dto.AddBookToLibraryRequest;
import com.aryil.api.library.dto.UserBookDto;
import com.aryil.api.library.entity.UserBook;
import com.aryil.api.library.mapper.UserBookMapper;
import com.aryil.api.library.repository.UserBookRepository;
import com.aryil.api.book.repository.BookRepository;
import com.aryil.api.user.entity.User;
import com.aryil.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLibraryService {

    private final UserBookRepository userBookRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;
    private final UserBookMapper mapper;

    public UserBookDto addToLibrary(String userEmail, AddBookToLibraryRequest req) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UUID bookId = req.getBookId();

        // Düzeltmiş methodu çağır:
        if (userBookRepo.existsByUser_IdAndBook_Id(user.getId(), bookId)) {
            throw new RuntimeException("Already in library");
        }

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        UserBook ub = UserBook.builder()
                .user(user)
                .book(book)
                .addedAt(Instant.now())
                .build();
        UserBook saved = userBookRepo.save(ub);
        return mapper.toDto(saved);
    }


    public List<UserBookDto> listLibrary(String userEmail) {
        var user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userBookRepo.findByUser(user).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
