package com.ujjwal.library_management.service;

import com.ujjwal.library_management.dto.BookRequest;
import com.ujjwal.library_management.dto.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse addBook(BookRequest request);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookRequest request);

    BookResponse patchBook(Long id, BookRequest request);

    void deleteBook(Long id);
}
