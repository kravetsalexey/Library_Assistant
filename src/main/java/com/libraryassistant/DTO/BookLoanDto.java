package com.libraryassistant.DTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookLoanDto {

    private Long userId;

    private Long bookId;
}
