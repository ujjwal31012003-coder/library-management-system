package com.ujjwal.library_management.repository;
import com.ujjwal.library_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);
}
