package com.aryil.api.library.entity;

import com.aryil.api.book.entity.Book;
import com.aryil.api.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_books", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","book_id"})
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserBook {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;  // com.aryil.api.user.entity.User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    private Book book;  // com.aryil.api.book.entity.Book

    @Column(name="added_at", updatable = false)
    private Instant addedAt;
}
