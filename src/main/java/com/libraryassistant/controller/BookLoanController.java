package com.libraryassistant.controller;

import com.libraryassistant.DTO.BookLoanDto;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.service.BookLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class BookLoanController {

    private final BookLoanService bookLoanService;

    @PutMapping("/take")
    public ResponseEntity<?> takeBook(@RequestBody BookLoanDto bookLoanDto){
        return ResponseEntity.ok(bookLoanService.takeBook(bookLoanDto));
    }

    @PostMapping("/return")
    public ResponseEntity<BookLoan> returnBook(@RequestParam Long bookLoanId){
            return ResponseEntity.ok(bookLoanService.returnBook(bookLoanId));
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
    @GetMapping("/specific")
    public ResponseEntity<List<BookLoan>> getSpecificBookLoan(@RequestParam Long userId, @RequestParam Long bookId){
        return ResponseEntity.ok(bookLoanService.getSpecificBookLoan(userId,bookId));
    }

    public BookLoanController(BookLoanService bookLoanService){
        this.bookLoanService = bookLoanService;
    }

}
