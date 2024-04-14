package com.libraryassistant.controller;

import com.libraryassistant.entity.Book;
import com.libraryassistant.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getBook(@RequestParam Long id){
    return ResponseEntity.ok(bookService.getBook(id));
    }
    @GetMapping("/getall")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Book> deleteBook(@RequestParam Long id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @GetMapping("/mostpopular")
    public ResponseEntity<Book> getMostPopularBook(LocalDate startDate, LocalDate endDate){
        return ResponseEntity.ok(bookService.getMostPopularBook(startDate,endDate));
    }
}
