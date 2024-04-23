package com.libraryassistant.controller;

import com.libraryassistant.DTO.BookLoanDto;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.service.BookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-loans-management")
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    @PostMapping("/managed-book-loans")
    public ResponseEntity<?> takeBook(@RequestBody BookLoanDto bookLoanDto){
        return ResponseEntity.ok(bookLoanService.takeBook(bookLoanDto));
    }

    @PutMapping("/managed-book-loans/{id}")
    public ResponseEntity<BookLoan> returnBook(@RequestParam Long bookLoanId){
            return ResponseEntity.ok(bookLoanService.returnBook(bookLoanId));
    }

    @GetMapping("/managed-user-loans/{id}")
    public ResponseEntity<List<BookLoan>> getUserBookLoans(@RequestParam Long userId){
        return ResponseEntity.ok(bookLoanService.getUserBookLoans(userId));
    }

    @GetMapping("/managed-book-loans/{id}")
    public ResponseEntity<List<BookLoan>> getBookLoans(@RequestParam Long bookId){
        return ResponseEntity.ok(bookLoanService.getBookLoans(bookId));
    }

    @GetMapping("/managed-book-loans")
    public ResponseEntity<List<BookLoan>> getAllBookLoan(){
        return ResponseEntity.ok(bookLoanService.getAllBookLoans());
    }

    @GetMapping("/managed-book-loans/specific")
    public ResponseEntity<List<BookLoan>> getSpecificBookLoan(@RequestParam Long userId, @RequestParam Long bookId){
        return ResponseEntity.ok(bookLoanService.getSpecificBookLoan(userId,bookId));
    }

    @DeleteMapping("/managed-book-loans/{id}")
    public ResponseEntity<BookLoan> deleteBookLoan(@RequestParam Long bookLoanId){
        return ResponseEntity.ok(bookLoanService.deleteBookLoan(bookLoanId));
    }
}
