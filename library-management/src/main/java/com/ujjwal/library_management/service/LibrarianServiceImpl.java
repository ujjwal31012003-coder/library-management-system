package com.ujjwal.library_management.service;

import com.ujjwal.library_management.dto.*;
import com.ujjwal.library_management.exception.ResourceNotFoundException;
import com.ujjwal.library_management.entity.Librarian;
import com.ujjwal.library_management.repository.LibrarianRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service 
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;

    private final PasswordEncoder passwordEncoder;

    public LibrarianServiceImpl(
            LibrarianRepository librarianRepository,
            PasswordEncoder passwordEncoder) {

        this.librarianRepository = librarianRepository;
        this.passwordEncoder = passwordEncoder;



    }
    @Override
    public LibrarianResponse createLibrarian(LibrarianRequest request) {

        if(librarianRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        }


        Librarian librarian = new Librarian();

        
        librarian.setUsername(request.getUsername());
        librarian.setPassword(passwordEncoder.encode(request.getPassword()));
        librarian.setRole(request.getRole());


        librarian.setCreatedAt(LocalDateTime.now());


        Librarian savedLibrarian = librarianRepository.save(librarian);

        LibrarianResponse response = new LibrarianResponse();

        response.setId(savedLibrarian.getId());

        response.setUsername(savedLibrarian.getUsername());
        response.setRole(savedLibrarian.getRole());



        return response;
    }
    @Override
    public List<LibrarianResponse> getLibrarians() {

        List<Librarian> librarians = librarianRepository.findAll();

        List<LibrarianResponse> responseList = new ArrayList<>();

        for (Librarian librarian : librarians) {

            LibrarianResponse response = new LibrarianResponse();

            response.setId(librarian.getId());

            response.setUsername(librarian.getUsername());
            response.setRole(librarian.getRole());


            responseList.add(response);
        }

        return responseList;
    }
    @Override
    public LibrarianResponse getLibrarianById(Long id) {

        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Librarian not found with id: " + id));

        LibrarianResponse response = new LibrarianResponse();

        response.setId(librarian.getId());
        response.setRole(librarian.getRole());
        response.setUsername(librarian.getUsername());

        return response;
    }
    @Override
    public LibrarianResponse updateLibrarian(Long id, LibrarianRequest request) {

        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));

        librarian.setRole(request.getRole());
        librarian.setUsername(request.getUsername());
        librarian.setPassword(
                passwordEncoder.encode(request.getPassword())
        );




        Librarian updatedLibrarian = librarianRepository.save(librarian);

        LibrarianResponse response = new LibrarianResponse();
         response.setId(updatedLibrarian.getId());
        response.setRole(updatedLibrarian.getRole());

        response.setUsername(updatedLibrarian.getUsername());



        return response;
    }

    @Override
    public void deleteLibrarian(Long id) {

        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));

        librarianRepository.delete(librarian);
    }
    @Override
    public LibrarianResponse patchLibrarian(Long id, LibrarianRequest request) {

        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));



        if (request.getUsername() != null) {
            librarian.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            librarian.setPassword(
                    passwordEncoder.encode(request.getPassword())
            );
        }
        if(request.getRole() != null) {
            librarian.setRole(request.getRole());
        }


        Librarian updatedLibrarian = librarianRepository.save(librarian);

        LibrarianResponse response = new LibrarianResponse();

        response.setId(updatedLibrarian.getId());
        response.setUsername(updatedLibrarian.getUsername());
        response.setRole(updatedLibrarian.getRole());

        return response;
    }




}
