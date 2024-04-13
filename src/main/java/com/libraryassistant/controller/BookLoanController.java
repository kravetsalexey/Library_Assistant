package com.libraryassistant.controller;

import com.libraryassistant.DTO.BookLoanDto;
import com.libraryassistant.DTO.ReturnBookDto;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.service.BookLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class BookLoanController {

    private final BookLoanService bookLoanService;

    @PostMapping("/take")
    public ResponseEntity<?> takeBook(@RequestBody BookLoanDto bookLoanDto){
        return ResponseEntity.ok(bookLoanService.takeBook(bookLoanDto));
    }

    @PostMapping("/return")
    public ResponseEntity<BookLoan> returnBook(@RequestBody ReturnBookDto returnBookDto){
            return ResponseEntity.ok(bookLoanService.returnBook(returnBookDto));
    }

    @GetMapping("/getUserLoans")
    public ResponseEntity<List<BookLoan>> getUserBookLoans(@RequestParam Long userId){
        return ResponseEntity.ok(bookLoanService.getUserBookLoans(userId));
    }

    @GetMapping("/getBookLoans")
    public ResponseEntity<List<BookLoan>> getBookLoans(@RequestParam Long bookId){
        return ResponseEntity.ok(bookLoanService.getBookLoans(bookId));
    }


    @GetMapping("/getall")
    public ResponseEntity<List<BookLoan>> getAllBookLoan(){
        return ResponseEntity.ok(bookLoanService.getAllBookLoans());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BookLoan> deleteBookLoan(@RequestParam Long bookLoanId){
        return ResponseEntity.ok(bookLoanService.deleteBookLoan(bookLoanId));
    }

    public BookLoanController(BookLoanService bookLoanService){
        this.bookLoanService = bookLoanService;
    }

}
