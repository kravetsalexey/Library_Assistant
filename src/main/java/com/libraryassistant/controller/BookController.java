package com.libraryassistant.controller;

import com.libraryassistant.entity.Book;
import com.libraryassistant.repository.BookRepository;
import com.libraryassistant.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book addedBook = bookService.addBook(book);
        return ResponseEntity.created(URI.create("/books/add" + addedBook.getId())).body(addedBook);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getBook(@RequestParam Long id){
    return ResponseEntity.ok(bookService.getBook(id));
    }
    @GetMapping("/getall")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Book> deleteBook(@RequestParam Long id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @GetMapping("/mostpopular")
    public ResponseEntity<Book> getMostPopularBook(LocalDate startDate, LocalDate endDate){
        Book book = bookService.getMostPopularBook(startDate,endDate);
        return ResponseEntity.ok(book);
    }
}
