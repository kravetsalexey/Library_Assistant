package com.libraryassistant.DTO;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public class ReturnBookDto {

    private Long bookLoanId;

    public  ReturnBookDto(){
    }

    public ReturnBookDto(Long bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public Long getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(Long bookLoanId) {
        this.bookLoanId = bookLoanId;
    }
}
