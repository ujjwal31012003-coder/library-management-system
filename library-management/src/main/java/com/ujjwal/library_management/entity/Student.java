package com.ujjwal.library_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "students")

public class Student {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      @Column(name = "student_id", nullable = false, unique = true)
        private String studentId;
      @Column(nullable = false)
        private String name;
      @Column(nullable = false)
      private Integer age;
      @Column(nullable = false, unique = true)
        private String email;
      private String phone;
      private String course;
      private Integer year;
      @Column(name = "created_at")
        private LocalDateTime createdAt;
      @Column(name = "updated_at")
      private LocalDateTime updatedAt;

      public Student(){
          
      }


    }



