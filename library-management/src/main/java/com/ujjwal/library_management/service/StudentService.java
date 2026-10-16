package com.ujjwal.library_management.service;

import com.ujjwal.library_management.dto.StudentRequest;
import com.ujjwal.library_management.dto.StudentResponse;
import java.util.List;
public interface StudentService {

    StudentResponse createStudent(StudentRequest request);
     List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    StudentResponse updateStudent(Long id, StudentRequest request);
    void deleteStudent(Long id);
    StudentResponse patchStudent(Long id, StudentRequest request);
}
