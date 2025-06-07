package com.aryil.api.library.repository;

import com.aryil.api.library.entity.UserBook;
import com.aryil.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, UUID> {
    List<UserBook> findByUser(User user);
    boolean existsByUser_IdAndBook_Id(UUID userId, UUID bookId);
}
