package com.libraryassistant.controller;

import com.libraryassistant.entity.Book;
import com.libraryassistant.repository.BookRepository;
import com.libraryassistant.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books-management")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/managed-books/new-copy")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/managed-books/modification")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/managed-books/check-one-copy")
    public ResponseEntity<?> getBook(@RequestParam Long id){
    return ResponseEntity.ok(bookService.getBook(id));
    }
    @GetMapping("/managed-books/list")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/managed-books/popular")
    public ResponseEntity<Book> getMostPopularBook(LocalDate startDate, LocalDate endDate){
        return ResponseEntity.ok(bookService.getMostPopularBook(startDate,endDate));
    }

    @DeleteMapping("/managed-books/deletion")
    public ResponseEntity<Book> deleteBook(@RequestParam Long id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
