package com.libraryassistant.entity;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate borrowedDate;
    private LocalDate returnDate;


    public BookLoan() {
    }

    public BookLoan(User user, Book book) {
        this.user = user;
        this.book = book;
        this.borrowedDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public ResponseEntity<BookLoan> setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return null;
    }
}
