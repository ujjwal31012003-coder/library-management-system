package com.ujjwal.library_management.service;
import com.ujjwal.library_management.dto.BookIssueResponse;
import com.ujjwal.library_management.dto.BookIssueRequest;
import java.util.List;

public interface BookIssueService {
    BookIssueResponse createBookIssue(BookIssueRequest request);
    List<BookIssueResponse> getBookIssues();
    BookIssueResponse getBookIssuebyID(Long id);
    BookIssueResponse updateBookIssue(Long id, BookIssueRequest request);
    void deleteBookIssue(Long id);
    BookIssueResponse patchBookIssue(Long id, BookIssueRequest request);
    BookIssueResponse returnBook(Long id);

}
