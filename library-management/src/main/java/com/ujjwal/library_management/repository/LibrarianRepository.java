package com.ujjwal.library_management.repository;

import com.ujjwal.library_management.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian,Long> {
    Optional<Librarian> findByUsername(String username);
    boolean existsByUsername(String username);
}


