package ServiceTests;

import com.libraryassistant.entity.Book;
import com.libraryassistant.exceptions.BookNotFoundException;
import com.libraryassistant.exceptions.EntityAlreadyExistsException;
import com.libraryassistant.exceptions.MostPopularBookNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.BookRepository;
import com.libraryassistant.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ContextConfiguration(classes = BookService.class)
public class BookServiceIntegrationTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testAddBook_Success() {
        Book book = new Book();
        book.setTitle("Сказки");
        book.setAuthor("Пушкин");
        when(bookRepository.save(book)).thenReturn(book);

        Book addedBook = bookService.addBook(book);

        assertNotNull(addedBook);
        assertEquals(book.getTitle(), addedBook.getTitle());
        assertEquals(book.getAuthor(), addedBook.getAuthor());
    }

    @Test
    public void testAddBook_BookAlreadyExists() {
        Book book = new Book();
        book.setTitle("Сказки");
        book.setAuthor("Пушкин");
        List<Book> existingBooks = new ArrayList<>();
        existingBooks.add(book);
        when(bookRepository.findAll()).thenReturn(existingBooks);

        assertThrows(EntityAlreadyExistsException.class, () -> bookService.addBook(book));
    }

    @Test
    public void testUpdateBook_Success() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book updatedBook = bookService.updateBook(book);

        assertNotNull(updatedBook);
        assertEquals(book.getId(), updatedBook.getId());
    }

    @Test
    public void testUpdateBook_BookNotFound() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(book));
    }

    @Test
    public void testGetMostPopularBook_Success() {
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        Book book = new Book();
        book.setId(1L);
        when(bookLoanRepository.findMostPopularBookId(startDate, endDate)).thenReturn(book.getId());
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Book mostPopularBook = bookService.getMostPopularBook(startDate, endDate);

        assertNotNull(mostPopularBook);
        assertEquals(book.getId(), mostPopularBook.getId());
    }

    @Test
    public void testGetMostPopularBook_BookNotFound() {
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        when(bookLoanRepository.findMostPopularBookId(startDate, endDate)).thenReturn(null);

        assertThrows(MostPopularBookNotFoundException.class, () -> bookService.getMostPopularBook(startDate, endDate));
    }

    @Test
    public void testGetBook_Success() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBook(bookId);

        assertNotNull(foundBook);
        assertEquals(book.getId(), foundBook.getId());
    }

    @Test
    public void testGetBook_BookNotFound() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBook(bookId));
    }

    @Test
    public void testDeleteBook_Success() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book deletedBook = bookService.deleteBook(bookId);

        assertNotNull(deletedBook);
        assertEquals(book.getId(), deletedBook.getId());
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void testDeleteBook_BookNotFound() {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId));
    }

    @Test
    public void testGetAllBooks_Success() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1L);
        bookList.add(book1);
        Book book2 = new Book();
        book2.setId(2L);
        bookList.add(book2);
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> foundBooks = bookService.getAllBooks();

        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());
    }
}
