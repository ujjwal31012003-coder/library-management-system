package com.ujjwal.library_management.controller;

import com.ujjwal.library_management.dto.LibrarianRequest;
import com.ujjwal.library_management.dto.LibrarianResponse;
import com.ujjwal.library_management.entity.Librarian;
import com.ujjwal.library_management.service.LibrarianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService){
        this.librarianService = librarianService;
    }

    @PostMapping
    public LibrarianResponse createLibrarian(
            @Valid @RequestBody LibrarianRequest request) {

        return librarianService.createLibrarian(request);

    }
    @GetMapping
    public List<LibrarianResponse> getLibrarians(){

        return librarianService.getLibrarians();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibrarianResponse> getLibrarianById(@PathVariable Long id) {

        LibrarianResponse response = librarianService.getLibrarianById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibrarianResponse> updateStudent(
            @PathVariable Long id,
            @RequestBody LibrarianRequest request) {

        LibrarianResponse response = librarianService.updateLibrarian(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibrarian(@PathVariable Long id) {

        librarianService.deleteLibrarian(id);

        return ResponseEntity.ok("Librarian deleted successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LibrarianResponse> patchStudent(
            @PathVariable Long id,
            @RequestBody LibrarianRequest request) {

        LibrarianResponse response = librarianService.patchLibrarian(id, request);

        return ResponseEntity.ok(response);

    }
}
