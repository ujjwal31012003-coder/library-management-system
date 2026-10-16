package com.ujjwal.library_management.controller;

import com.ujjwal.library_management.dto.StudentRequest;
import com.ujjwal.library_management.dto.StudentResponse;
import com.ujjwal.library_management.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController { 

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse createStudent(
            @Valid @RequestBody StudentRequest request) {

        return studentService.createStudent(request);

    }
    @GetMapping
    public List<StudentResponse> getAllStudents(){

        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {

        StudentResponse response = studentService.getStudentById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequest request) {

        StudentResponse response = studentService.updateStudent(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponse> patchStudent(
            @PathVariable Long id,
            @RequestBody StudentRequest request) {

        StudentResponse response = studentService.patchStudent(id, request);

        return ResponseEntity.ok(response);
    }
}
