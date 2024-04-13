package com.libraryassistant.service;

import com.libraryassistant.DTO.BookLoanDto;
import com.libraryassistant.DTO.ReturnBookDto;
import com.libraryassistant.entity.Book;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.entity.User;
import com.example.libraryassistant.exceptions.*;
import com.libraryassistant.exceptions.*;
import com.libraryassistant.repository.BookLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public BookLoan takeBook(BookLoanDto bookLoanDto) {
        User user = userService.getUser(bookLoanDto.getUserId());
        Book book = bookService.getBook(bookLoanDto.getBookId());
        BookLoan bookLoan = new BookLoan(user,book, LocalDate.now());
        if (book.getCount() > 0){
            book.setCount(book.getCount() - 1);
            bookService.updateBook(book);
            return bookLoanRepository.save(bookLoan);
        }
        throw new OutOfBooksException();
    }

    public BookLoan returnBook(ReturnBookDto returnBookDto){
        BookLoan bookLoan = bookLoanRepository.findById(returnBookDto.getBookLoanId()).orElseThrow(BookLoanNotFoundException::new);
        bookLoan.setReturnDate(LocalDate.now());
        Book book = bookLoan.getBook();
        book.setCount(book.getCount() + 1);
        return  bookLoanRepository.save(bookLoan);
    }


    public List<BookLoan> getUserBookLoans(Long id){
        List<BookLoan> bookLoans = bookLoanRepository.findByUserId(id);
        userService.getUser(id);
        if (!bookLoans.isEmpty()){
            return bookLoans;
        }
        throw new NoAnyUserLoansException();
    }

    public BookLoan deleteBookLoan(Long id){
        BookLoan bookLoan = bookLoanRepository.findById(id).orElseThrow(BookLoanNotFoundException::new);
        bookLoanRepository.delete(bookLoan);
        return bookLoan;
    }


    public List<BookLoan> getAllBookLoans() {
        List<BookLoan> bookLoans = bookLoanRepository.findAll();
        if (!bookLoans.isEmpty()) {
            return bookLoans;
        }
        throw new NoAnyBookLoansException();
    }

    public List<BookLoan> getBookLoans(Long id) {
        List<BookLoan> bookLoans = bookLoanRepository.findByBookId(id);
        bookService.getBook(id);
        if (!bookLoans.isEmpty()){
            return bookLoans;
        }
        throw new NoAnyBookLoanException();
    }
}
