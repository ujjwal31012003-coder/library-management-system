package com.ujjwal.library_management.service;

import com.ujjwal.library_management.dto.BookIssueResponse;
import com.ujjwal.library_management.dto.BookIssueRequest;
import com.ujjwal.library_management.exception.BusinessException;
import com.ujjwal.library_management.exception.ResourceNotFoundException;
import com.ujjwal.library_management.dto.StudentResponse;
import com.ujjwal.library_management.entity.BookIssue;
import com.ujjwal.library_management.repository.BookIssueRepository;
import com.ujjwal.library_management.service.BookIssueService;
import org.apache.coyote.Request;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import com.ujjwal.library_management.entity.Book;
import com.ujjwal.library_management.repository.BookRepository;

@Service

public class BookIssueServiceimpl implements BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;

    public BookIssueServiceimpl(
            BookIssueRepository bookIssueRepository,
            BookRepository bookRepository) {

        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookIssueResponse createBookIssue(BookIssueRequest request) {

        // 1. Check if this book is already issued
        if (bookIssueRepository.existsByBookIdAndStatus(
                request.getBookId(), "ISSUED")) {

            throw new BusinessException(
                    "Book is already issued"
            );
        }

        // 2. Find the book
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: "
                                        + request.getBookId()
                        )
                );

        // 3. Check book availability
        if (book.getAvailableQuantity() <= 0) {

            throw new BusinessException(
                    "Book is not available for issue"
            );
        }

        // 4. Decrease available quantity
        book.setAvailableQuantity(
                book.getAvailableQuantity() - 1
        );

        bookRepository.save(book);

        // 5. Create BookIssue
        BookIssue bookIssue = new BookIssue();

        bookIssue.setStudentId(request.getStudentId());
        bookIssue.setBookId(request.getBookId());

        // Automatically set issue date
        LocalDate issueDate = LocalDate.now();
        bookIssue.setIssueDate(issueDate);

        // Due date = 14 days from issue date
        bookIssue.setDueDate(
                issueDate.plusDays(14)
        );

        // Book has not been returned yet
        bookIssue.setReturnDate(null);

        // New issue starts with ISSUED status
        bookIssue.setStatus("ISSUED");

        // 6. Save issue
        BookIssue savedBookIssue =
                bookIssueRepository.save(bookIssue);

        // 7. Create response
        BookIssueResponse bookIssueResponse =
                new BookIssueResponse();

        bookIssueResponse.setId(savedBookIssue.getId());
        bookIssueResponse.setStudentId(
                savedBookIssue.getStudentId()
        );
        bookIssueResponse.setBookId(
                savedBookIssue.getBookId()
        );
        bookIssueResponse.setIssueDate(
                savedBookIssue.getIssueDate()
        );
        bookIssueResponse.setDueDate(
                savedBookIssue.getDueDate()
        );
        bookIssueResponse.setReturnDate(
                savedBookIssue.getReturnDate()
        );
        bookIssueResponse.setStatus(
                savedBookIssue.getStatus()
        );

        return bookIssueResponse;
    }
    @Override
    public List<BookIssueResponse> getBookIssues(){
        List<BookIssue> bookIssues = bookIssueRepository.findAll();
        List<BookIssueResponse> bookIssueResponseList = new ArrayList<>();
        for (BookIssue bookIssue : bookIssues) {

            BookIssueResponse bookIssueResponse = new BookIssueResponse();

            bookIssueResponse.setId(bookIssue.getId());
            bookIssueResponse.setBookId(bookIssue.getBookId());
            bookIssueResponse.setStudentId(bookIssue.getStudentId());
            bookIssueResponse.setReturnDate(bookIssue.getReturnDate());
            bookIssueResponse.setIssueDate(bookIssue.getIssueDate());
            bookIssueResponse.setDueDate(bookIssue.getDueDate());
            bookIssueResponse.setStatus(bookIssue.getStatus());

            bookIssueResponseList.add(bookIssueResponse);
        }
        return bookIssueResponseList;

    }
    @Override
    public BookIssueResponse getBookIssuebyID(Long id) {
        BookIssue bookIssue = bookIssueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book issue not found with id: " + id));
        BookIssueResponse bookIssueResponse = new BookIssueResponse();
        bookIssueResponse.setId(bookIssue.getId());
        bookIssueResponse.setBookId(bookIssue.getBookId());
        bookIssueResponse.setStudentId(bookIssue.getStudentId());
        bookIssueResponse.setReturnDate(bookIssue.getReturnDate());
        bookIssueResponse.setIssueDate(bookIssue.getIssueDate());
        bookIssueResponse.setDueDate(bookIssue.getDueDate());
        bookIssueResponse.setStatus(bookIssue.getStatus());

        return bookIssueResponse;

    }
    @Override
    public BookIssueResponse updateBookIssue(Long id,BookIssueRequest request) {
        BookIssue bookIssue = bookIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Issue Not Found"));

        bookIssue.setBookId(request.getBookId());
        bookIssue.setStudentId(request.getStudentId());
        bookIssue.setReturnDate(request.getReturnDate());
        bookIssue.setIssueDate(request.getIssueDate());
        bookIssue.setDueDate(request.getDueDate());
        bookIssue.setStatus(request.getStatus());


        BookIssue updatedBookIssue = bookIssueRepository.save(bookIssue);

        BookIssueResponse bookIssueResponse = new BookIssueResponse();
        bookIssueResponse.setId(updatedBookIssue.getId());
        bookIssueResponse.setBookId(updatedBookIssue.getBookId());
        bookIssueResponse.setStudentId(updatedBookIssue.getStudentId());
        bookIssueResponse.setReturnDate(updatedBookIssue.getReturnDate());
        bookIssueResponse.setIssueDate(updatedBookIssue.getIssueDate());
        bookIssueResponse.setDueDate(updatedBookIssue.getDueDate());
        bookIssueResponse.setStatus(updatedBookIssue.getStatus());
        return bookIssueResponse;
    }
    @Override
    public void deleteBookIssue(Long id) {
        BookIssue bookIssue = bookIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Issue Not Found"));
        bookIssueRepository.delete(bookIssue);
    }
    @Override
    public BookIssueResponse patchBookIssue(Long id, BookIssueRequest request) {
        BookIssue bookIssue = bookIssueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Issue Not Found"));

        if (request.getBookId() != null) {
            bookIssue.setBookId(request.getBookId());
        }
        if (request.getStudentId() != null) {
            bookIssue.setStudentId(request.getStudentId());
        }
        if (request.getReturnDate() != null) {
            bookIssue.setReturnDate(request.getReturnDate());
        }
        if (request.getIssueDate() != null) {
            bookIssue.setIssueDate(request.getIssueDate());
        }
        if (request.getDueDate() != null) {
            bookIssue.setDueDate(request.getDueDate());
        }
        if (request.getStatus() != null) {
            bookIssue.setStatus(request.getStatus());
        }

        BookIssue updatedBookIssue = bookIssueRepository.save(bookIssue);
        BookIssueResponse bookIssueResponse = new BookIssueResponse();
        bookIssueResponse.setId(bookIssue.getId());
        bookIssueResponse.setBookId(bookIssue.getBookId());
        bookIssueResponse.setStudentId(bookIssue.getStudentId());
        bookIssueResponse.setReturnDate(bookIssue.getReturnDate());
        bookIssueResponse.setIssueDate(bookIssue.getIssueDate());
        bookIssueResponse.setDueDate(bookIssue.getDueDate());
        bookIssueResponse.setStatus(updatedBookIssue.getStatus());
        return bookIssueResponse;
    }
    @Override
    public BookIssueResponse returnBook(Long id) {

        BookIssue bookIssue = bookIssueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book issue not found with id: " + id
                        )
                );

        if (!"ISSUED".equals(bookIssue.getStatus())) {
            throw new BusinessException(
                    "Book is already returned"
            );
        }

        Book book = bookRepository.findById(bookIssue.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: "
                                        + bookIssue.getBookId()
                        )
                );

        book.setAvailableQuantity(
                book.getAvailableQuantity() + 1
        );

        bookRepository.save(book);

        bookIssue.setStatus("RETURNED");
        bookIssue.setReturnDate(LocalDate.from(LocalDateTime.now()));

        BookIssue updatedBookIssue =
                bookIssueRepository.save(bookIssue);

        BookIssueResponse response = new BookIssueResponse();

        response.setId(updatedBookIssue.getId());
        response.setBookId(updatedBookIssue.getBookId());
        response.setStudentId(updatedBookIssue.getStudentId());
        response.setIssueDate(updatedBookIssue.getIssueDate());
        response.setDueDate(updatedBookIssue.getDueDate());
        response.setReturnDate(updatedBookIssue.getReturnDate());
        response.setStatus(updatedBookIssue.getStatus());

        return response;
    }


}
