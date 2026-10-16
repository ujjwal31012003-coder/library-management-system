package com.ujjwal.library_management.dto;

import java.time.LocalDate;

public class BookIssueResponse {
    private Long  id;
    private Long bookId;
    private Long studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public BookIssueResponse() {

    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getBookId() {return bookId;}
    public void setBookId(Long bookId) {this.bookId = bookId;}
    public Long getStudentId() {return studentId;}
    public void setStudentId(Long studentId) {this.studentId = studentId;}
    public LocalDate getIssueDate() {return issueDate;}
    public void setIssueDate(LocalDate issueDate) {this.issueDate = issueDate;}
    public LocalDate getDueDate() {return dueDate;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}
    public LocalDate getReturnDate() {return returnDate;}
    public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}


}
