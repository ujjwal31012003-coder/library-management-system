package com.ujjwal.library_management.dto;

import java.time.LocalDate;

public class BookIssueRequest {
    private Long studentId;
    private Long bookId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private String status;
    public BookIssueRequest() {

    }
    public  Long getStudentId(){return studentId;}
    public void setStudentId(Long studentId){this.studentId = studentId;}
    public  Long getBookId(){return bookId;}
    public void setBookId(Long bookId){this.bookId = bookId;}
    public LocalDate getIssueDate(){return issueDate;}
    public void setIssueDate(LocalDate issueDate){this.issueDate = issueDate;}
    public   LocalDate getReturnDate(){return returnDate;}
    public void setReturnDate(LocalDate returnDate){this.returnDate = returnDate;}
    public   LocalDate getDueDate(){return dueDate;}
    public void setDueDate(LocalDate dueDate){this.dueDate = dueDate;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
}
