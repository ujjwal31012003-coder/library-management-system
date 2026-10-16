package com.ujjwal.library_management.controller;

import com.ujjwal.library_management.dto.BookIssueRequest;
import com.ujjwal.library_management.dto.BookIssueResponse;
import com.ujjwal.library_management.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/BookIssues")
public class BookIssueController {

    private final BookIssueService BookIssueService;

    public BookIssueController(BookIssueService BookIssueService){
        this.BookIssueService = BookIssueService;
    }

    @PostMapping
    public BookIssueResponse createBookIssue(@RequestBody BookIssueRequest request){

        return BookIssueService.createBookIssue(request);

    }
    @GetMapping
    public List<BookIssueResponse> getBookIssues(){

        return BookIssueService.getBookIssues();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookIssueResponse> getBookIssueByID(@PathVariable Long id) {

        BookIssueResponse response = BookIssueService.getBookIssuebyID(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BookIssueResponse> returnBook(
            @PathVariable Long id){

        BookIssueResponse response = BookIssueService.returnBook(id);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookIssue(@PathVariable Long id) {

        BookIssueService.deleteBookIssue(id);

        return ResponseEntity.ok("BookIssue deleted successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookIssueResponse> patchBookIssue(
            @PathVariable Long id,
            @RequestBody BookIssueRequest request) {

        BookIssueResponse response = BookIssueService.patchBookIssue(id, request);

        return ResponseEntity.ok(response);
    }
}
