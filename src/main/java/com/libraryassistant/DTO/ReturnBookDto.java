package com.libraryassistant.DTO;


public class ReturnBookDto {

    private Long bookLoanId;

    public  ReturnBookDto(){
    }

    public ReturnBookDto(Long bookLoanId) {
        this.bookLoanId = bookLoanId;
    }


}
