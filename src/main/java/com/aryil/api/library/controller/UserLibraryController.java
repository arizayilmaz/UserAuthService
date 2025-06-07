package com.aryil.api.library.controller;

import com.aryil.api.library.dto.AddBookToLibraryRequest;
import com.aryil.api.library.dto.UserBookDto;
import com.aryil.api.library.service.UserLibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users/me/library")
@RequiredArgsConstructor
public class UserLibraryController {

    private final UserLibraryService libraryService;

    @PostMapping
    public ResponseEntity<UserBookDto> add(
            @AuthenticationPrincipal UserDetails ud,
            @RequestBody @Valid AddBookToLibraryRequest req
    ) {
        return ResponseEntity.ok(
                libraryService.addToLibrary(ud.getUsername(), req)
        );
    }

    @GetMapping
    public ResponseEntity<List<UserBookDto>> list(
            @AuthenticationPrincipal UserDetails ud
    ) {
        return ResponseEntity.ok(
                libraryService.listLibrary(ud.getUsername())
        );
    }
}
