package com.libraryassistant.service;

import com.libraryassistant.entity.Book;
import com.libraryassistant.exceptions.BookNotFoundException;
import com.libraryassistant.exceptions.MostPopularBookNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }

    public Book getMostPopularBook(LocalDate startDate, LocalDate endDate) {
        return bookRepository.findById(bookLoanRepository.findMostPopularBookId(startDate, endDate)).orElseThrow(MostPopularBookNotFoundException::new);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public Book deleteBook(Long id){
        Book book = bookRepository.getReferenceById(id);
        bookRepository.delete(getBook(id));
        return book;
    }

}
