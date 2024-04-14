package com.libraryassistant.service;

import com.libraryassistant.entity.Book;
import com.libraryassistant.exceptions.BookNotFoundException;
import com.libraryassistant.exceptions.EntityAlreadyExistsException;
import com.libraryassistant.exceptions.MostPopularBookNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    public Book addBook(Book book){
        for (Book allBook : getAllBooks()) {
            if (book.getTitle().equals(allBook.getTitle()) && book.getAuthor().equals(allBook.getAuthor())){
                throw new EntityAlreadyExistsException();
            }
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Book book){
        if (bookRepository.findById(book.getId()).isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepository.updateBook(book.getId(),book.getTitle(),book.getAuthor(),book.getCount());
        return book;
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

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
