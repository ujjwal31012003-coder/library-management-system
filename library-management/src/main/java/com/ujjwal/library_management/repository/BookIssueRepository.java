package com.ujjwal.library_management.repository;
import com.ujjwal.library_management.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

    boolean existsByBookId(Long bookId);

    boolean existsByBookIdAndStatus(Long bookId, String status);
}
