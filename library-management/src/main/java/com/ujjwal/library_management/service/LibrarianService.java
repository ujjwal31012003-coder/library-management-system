package com.ujjwal.library_management.service;

import com.ujjwal.library_management.dto.LibrarianRequest;
import com.ujjwal.library_management.dto.LibrarianResponse;
import java.util.List;


public interface LibrarianService {
    LibrarianResponse createLibrarian(LibrarianRequest request);
    LibrarianResponse updateLibrarian(Long id, LibrarianRequest request);

    List<LibrarianResponse> getLibrarians();
    LibrarianResponse getLibrarianById(Long id);

    void deleteLibrarian(Long id);

    LibrarianResponse patchLibrarian(Long id, LibrarianRequest request);
    
}
