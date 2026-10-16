package com.ujjwal.library_management.service;

import com.ujjwal.library_management.dto.StudentRequest;
import com.ujjwal.library_management.exception.ResourceNotFoundException;
import com.ujjwal.library_management.dto.StudentResponse;
import com.ujjwal.library_management.entity.Student;
import com.ujjwal.library_management.repository.StudentRepository;
import com.ujjwal.library_management.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        if(studentRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }


        Student student = new Student();

        student.setStudentId(request.getStudentId());
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(request.getCourse());
        student.setYear(request.getYear());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);

        StudentResponse response = new StudentResponse();

        response.setId(savedStudent.getId());
        response.setStudentId(savedStudent.getStudentId());
        response.setName(savedStudent.getName());
        response.setAge(savedStudent.getAge());
        response.setEmail(savedStudent.getEmail());
        response.setPhone(savedStudent.getPhone());
        response.setCourse(savedStudent.getCourse());
        response.setYear(savedStudent.getYear());

        return response;
    }
    @Override
    public List<StudentResponse> getAllStudents() {

        List<Student> students = studentRepository.findAll();

        List<StudentResponse> responseList = new ArrayList<>();

        for (Student student : students) {

            StudentResponse response = new StudentResponse();

            response.setId(student.getId());
            response.setStudentId(student.getStudentId());
            response.setName(student.getName());
            response.setAge(student.getAge());
            response.setEmail(student.getEmail());
            response.setPhone(student.getPhone());
            response.setCourse(student.getCourse());
            response.setYear(student.getYear());

            responseList.add(response);
        }

        return responseList;
    }
    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setStudentId(student.getStudentId());
        response.setName(student.getName());
        response.setAge(student.getAge());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setCourse(student.getCourse());
        response.setYear(student.getYear());

        return response;
    }
    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setStudentId(request.getStudentId());
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(request.getCourse());
        student.setYear(request.getYear());

        student.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(student);

        StudentResponse response = new StudentResponse();

        response.setId(updatedStudent.getId());
        response.setStudentId(updatedStudent.getStudentId());
        response.setName(updatedStudent.getName());
        response.setAge(updatedStudent.getAge());
        response.setEmail(updatedStudent.getEmail());
        response.setPhone(updatedStudent.getPhone());
        response.setCourse(updatedStudent.getCourse());
        response.setYear(updatedStudent.getYear());

        return response;
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }
    @Override
    public StudentResponse patchStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        if (request.getStudentId() != null) {
            student.setStudentId(request.getStudentId());
        }

        if (request.getName() != null) {
            student.setName(request.getName());
        }

        if (request.getAge() != null) {
            student.setAge(request.getAge());
        }

        if (request.getEmail() != null) {
            student.setEmail(request.getEmail());
        }

        if (request.getPhone() != null) {
            student.setPhone(request.getPhone());
        }

        if (request.getCourse() != null) {
            student.setCourse(request.getCourse());
        }

        if (request.getYear() != null) {
            student.setYear(request.getYear());
        }

        student.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(student);

        StudentResponse response = new StudentResponse();

        response.setId(updatedStudent.getId());
        response.setStudentId(updatedStudent.getStudentId());
        response.setName(updatedStudent.getName());
        response.setAge(updatedStudent.getAge());
        response.setEmail(updatedStudent.getEmail());
        response.setPhone(updatedStudent.getPhone());
        response.setCourse(updatedStudent.getCourse());
        response.setYear(updatedStudent.getYear());

        return response;
    }
}