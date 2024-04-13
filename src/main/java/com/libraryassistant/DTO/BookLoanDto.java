package com.libraryassistant.DTO;


public class BookLoanDto {

    private Long userId;

    private Long bookId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public BookLoanDto(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
    public BookLoanDto(){
    }
}
