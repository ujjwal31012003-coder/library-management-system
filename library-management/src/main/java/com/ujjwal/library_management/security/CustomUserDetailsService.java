package com.ujjwal.library_management.security;

import com.ujjwal.library_management.entity.Librarian;
import com.ujjwal.library_management.repository.LibrarianRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LibrarianRepository librarianRepository;

    public CustomUserDetailsService(
            LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Librarian librarian = librarianRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Librarian not found: " + username));

        return User.builder()
                .username(librarian.getUsername())
                .password(librarian.getPassword())
                .roles(librarian.getRole())
                .build();
    }
}
