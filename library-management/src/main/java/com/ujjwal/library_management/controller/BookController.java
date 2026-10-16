package com.ujjwal.library_management.controller;
import jakarta.validation.Valid;

import com.ujjwal.library_management.dto.BookRequest;
import com.ujjwal.library_management.dto.BookResponse;
import com.ujjwal.library_management.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Add Book
    @PostMapping
    public BookResponse createBook(
            @Valid @RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    // Get All Books
    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get Book By Id
    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Update Book
    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable Long id,
            @RequestBody BookRequest request) {

        return bookService.updateBook(id, request);
    }

    // Patch Book
    @PatchMapping("/{id}")
    public BookResponse patchBook(
            @PathVariable Long id,
            @RequestBody BookRequest request) {

        return bookService.patchBook(id, request);
    }

    // Delete Book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}