package com.aryil.api.book.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Book {
    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String author;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookType type;  // enum BOOK, MANGA

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
}
