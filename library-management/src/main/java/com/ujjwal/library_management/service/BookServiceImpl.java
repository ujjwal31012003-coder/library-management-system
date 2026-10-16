package com.ujjwal.library_management.service;


import com.ujjwal.library_management.exception.ResourceNotFoundException;
import com.ujjwal.library_management.dto.BookRequest;
import com.ujjwal.library_management.dto.BookResponse;
import com.ujjwal.library_management.entity.Book;
import com.ujjwal.library_management.repository.BookRepository;
import com.ujjwal.library_management.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponse addBook(BookRequest request) {

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("Book with this ISBN already exists");
        }

        Book book = new Book();

        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setCategory(request.getCategory());
        book.setQuantity(request.getQuantity());

        // Initially all books are available
        book.setAvailableQuantity(request.getQuantity());

        book.setShelfNumber(request.getShelfNumber());

        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());

        Book savedBook = bookRepository.save(book);

        BookResponse response = new BookResponse();

        response.setId(savedBook.getId());
        response.setIsbn(savedBook.getIsbn());
        response.setTitle(savedBook.getTitle());
        response.setAuthor(savedBook.getAuthor());
        response.setPublisher(savedBook.getPublisher());
        response.setCategory(savedBook.getCategory());
        response.setQuantity(savedBook.getQuantity());
        response.setAvailableQuantity(savedBook.getAvailableQuantity());
        response.setShelfNumber(savedBook.getShelfNumber());

        return response;
    }

    @Override
    public List<BookResponse> getAllBooks() {

        List<Book> books = bookRepository.findAll();

        List<BookResponse> responseList = new ArrayList<>();

        for (Book book : books) {

            BookResponse response = new BookResponse();

            response.setId(book.getId());
            response.setIsbn(book.getIsbn());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setPublisher(book.getPublisher());
            response.setCategory(book.getCategory());
            response.setQuantity(book.getQuantity());
            response.setAvailableQuantity(book.getAvailableQuantity());
            response.setShelfNumber(book.getShelfNumber());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public BookResponse getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setIsbn(book.getIsbn());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublisher(book.getPublisher());
        response.setCategory(book.getCategory());
        response.setQuantity(book.getQuantity());
        response.setAvailableQuantity(book.getAvailableQuantity());
        response.setShelfNumber(book.getShelfNumber());

        return response;
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setCategory(request.getCategory());
        book.setQuantity(request.getQuantity());
        book.setAvailableQuantity(request.getQuantity());
        book.setShelfNumber(request.getShelfNumber());

        book.setUpdatedAt(LocalDateTime.now());

        Book updatedBook = bookRepository.save(book);

        BookResponse response = new BookResponse();

        response.setId(updatedBook.getId());
        response.setIsbn(updatedBook.getIsbn());
        response.setTitle(updatedBook.getTitle());
        response.setAuthor(updatedBook.getAuthor());
        response.setPublisher(updatedBook.getPublisher());
        response.setCategory(updatedBook.getCategory());
        response.setQuantity(updatedBook.getQuantity());
        response.setAvailableQuantity(updatedBook.getAvailableQuantity());
        response.setShelfNumber(updatedBook.getShelfNumber());

        return response;
    }

    @Override
    public BookResponse patchBook(Long id, BookRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (request.getTitle() != null)
            book.setTitle(request.getTitle());

        if (request.getAuthor() != null)
            book.setAuthor(request.getAuthor());

        if (request.getPublisher() != null)
            book.setPublisher(request.getPublisher());

        if (request.getCategory() != null)
            book.setCategory(request.getCategory());

        if (request.getShelfNumber() != null)
            book.setShelfNumber(request.getShelfNumber());

        if (request.getQuantity() != null) {
            book.setQuantity(request.getQuantity());
            book.setAvailableQuantity(request.getQuantity());
        }

        book.setUpdatedAt(LocalDateTime.now());

        Book updatedBook = bookRepository.save(book);

        BookResponse response = new BookResponse();

        response.setId(updatedBook.getId());
        response.setIsbn(updatedBook.getIsbn());
        response.setTitle(updatedBook.getTitle());
        response.setAuthor(updatedBook.getAuthor());
        response.setPublisher(updatedBook.getPublisher());
        response.setCategory(updatedBook.getCategory());
        response.setQuantity(updatedBook.getQuantity());
        response.setAvailableQuantity(updatedBook.getAvailableQuantity());
        response.setShelfNumber(updatedBook.getShelfNumber());

        return response;
    }

    @Override
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookRepository.delete(book);
    }
}