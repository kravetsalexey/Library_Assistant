package ServiceTests;

import com.libraryassistant.DTO.BookLoanDto;
import com.libraryassistant.DTO.ReturnBookDto;
import com.libraryassistant.entity.Book;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.entity.User;
import com.libraryassistant.exceptions.*;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.service.BookLoanService;
import com.libraryassistant.service.BookService;
import com.libraryassistant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@ContextConfiguration(classes = BookLoanService.class)
@SpringBootTest
public class BookLoanServiceIntegrationTest {

    @MockBean
    private BookLoanRepository bookLoanRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private BookService bookService;

    @Autowired
    private BookLoanService bookLoanService;


    @Test
    public void testTakeBook_Success() {
        BookLoanDto bookLoanDto = new BookLoanDto();
        bookLoanDto.setUserId(1L);
        bookLoanDto.setBookId(1L);
        User user = new User();
        user.setId(1L);
        Book book = new Book();
        book.setId(1L);
        book.setCount(1);
        BookLoan bookLoan = new BookLoan(user, book);
        when(userService.getUser(bookLoanDto.getUserId())).thenReturn(user);
        when(bookService.getBook(bookLoanDto.getBookId())).thenReturn(book);
        when(bookLoanRepository.save(bookLoan)).thenReturn(bookLoan);

        assertNotNull(bookLoan);
        assertEquals(user.getId(), bookLoan.getUser().getId());
        assertEquals(book.getId(), bookLoan.getBook().getId());
        assertEquals(LocalDate.now(), bookLoan.getBorrowedDate());
    }

    @Test
    public void testTakeBook_OutOfBooksException() {
        BookLoanDto bookLoanDto = new BookLoanDto();
        bookLoanDto.setUserId(1L);
        bookLoanDto.setBookId(1L);
        User user = new User();
        user.setId(1L);
        Book book = new Book();
        book.setId(1L);
        book.setCount(0);
        when(userService.getUser(bookLoanDto.getUserId())).thenReturn(user);
        when(bookService.getBook(bookLoanDto.getBookId())).thenReturn(book);

        assertThrows(OutOfBooksException.class, () -> bookLoanService.takeBook(bookLoanDto));
    }

    @Test
    public void testReturnBook_Success() {
        ReturnBookDto returnBookDto = new ReturnBookDto();
        returnBookDto.setBookLoanId(1L);
        BookLoan bookLoan = new BookLoan();
        bookLoan.setId(1L);
        bookLoan.setBook(new Book());
        bookLoan.getBook().setCount(0);
        when(bookLoanRepository.findById(returnBookDto.getBookLoanId())).thenReturn(Optional.of(bookLoan));
        when(bookLoanRepository.save(bookLoan)).thenReturn(bookLoan);

        BookLoan returnedBookLoan = bookLoanService.returnBook(returnBookDto);

        assertNotNull(returnedBookLoan);
        assertNotNull(returnedBookLoan.getReturnDate());
        assertEquals(1, returnedBookLoan.getBook().getCount());
    }

    @Test
    public void testGetUserBookLoans_Success() {
        Long userId = 1L;
        List<BookLoan> bookLoans = new ArrayList<>();
        BookLoan bookLoan1 = new BookLoan();
        bookLoans.add(bookLoan1);
        when(bookLoanRepository.findByUserId(userId)).thenReturn(bookLoans);
        when(userService.getUser(userId)).thenReturn(new User());

        List<BookLoan> foundBookLoans = bookLoanService.getUserBookLoans(userId);

        assertNotNull(foundBookLoans);
        assertFalse(foundBookLoans.isEmpty());
    }
    @Test
    public void testDeleteBookLoan_Success() {
        Long bookLoanId = 1L;
        BookLoan bookLoan = new BookLoan();
        bookLoan.setId(bookLoanId);
        when(bookLoanRepository.findById(bookLoanId)).thenReturn(Optional.of(bookLoan));
        doNothing().when(bookLoanRepository).delete(bookLoan);

        BookLoan deletedBookLoan = bookLoanService.deleteBookLoan(bookLoanId);

        assertNotNull(deletedBookLoan);
        assertEquals(bookLoanId, deletedBookLoan.getId());
    }

    @Test
    public void testDeleteBookLoan_BookLoanNotFoundException() {
        Long bookLoanId = 1L;
        when(bookLoanRepository.findById(bookLoanId)).thenReturn(Optional.empty());

        assertThrows(BookLoanNotFoundException.class, () -> bookLoanService.deleteBookLoan(bookLoanId));
    }

    @Test
    public void testGetAllBookLoans_Success() {
        List<BookLoan> bookLoans = new ArrayList<>();
        BookLoan bookLoan1 = new BookLoan();
        BookLoan bookLoan2 = new BookLoan();
        bookLoans.add(bookLoan1);
        bookLoans.add(bookLoan2);
        when(bookLoanRepository.findAll()).thenReturn(bookLoans);

        List<BookLoan> foundBookLoans = bookLoanService.getAllBookLoans();

        assertNotNull(foundBookLoans);
        assertFalse(foundBookLoans.isEmpty());
        assertEquals(2, foundBookLoans.size());
    }

    @Test
    public void testGetAllBookLoans_NoAnyBookLoansException() {
        when(bookLoanRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NoAnyBookLoansException.class, () -> bookLoanService.getAllBookLoans());
    }

    @Test
    public void testGetBookLoans_Success() {
        Long bookId = 1L;
        List<BookLoan> bookLoans = new ArrayList<>();
        BookLoan bookLoan1 = new BookLoan();
        BookLoan bookLoan2 = new BookLoan();
        bookLoans.add(bookLoan1);
        bookLoans.add(bookLoan2);
        when(bookLoanRepository.findByBookId(bookId)).thenReturn(bookLoans);
        when(bookService.getBook(bookId)).thenReturn(new Book());

        List<BookLoan> foundBookLoans = bookLoanService.getBookLoans(bookId);

        assertNotNull(foundBookLoans);
        assertFalse(foundBookLoans.isEmpty());
        assertEquals(2, foundBookLoans.size());
    }

    @Test
    public void testGetBookLoans_NoAnyBookLoanException() {
        Long bookId = 1L;
        when(bookLoanRepository.findByBookId(bookId)).thenReturn(new ArrayList<>());
        when(bookService.getBook(bookId)).thenReturn(new Book());

        assertThrows(NoAnyBookLoanException.class, () -> bookLoanService.getBookLoans(bookId));
    }

    @Test
    public void testGetSpecificBookLoan_Success() {
        Long userId = 1L;
        Long bookId = 2L;
        List<BookLoan> bookLoans = new ArrayList<>();
        BookLoan bookLoan1 = new BookLoan();
        bookLoans.add(bookLoan1);
        when(bookLoanRepository.findByUserAndBook(any(User.class), any(Book.class))).thenReturn(bookLoans);
        when(userService.getUser(userId)).thenReturn(new User());
        when(bookService.getBook(bookId)).thenReturn(new Book());

        List<BookLoan> foundBookLoans = bookLoanService.getSpecificBookLoan(userId, bookId);

        assertNotNull(foundBookLoans);
        assertFalse(foundBookLoans.isEmpty());
        assertEquals(1, foundBookLoans.size());
    }

    @Test
    public void testGetSpecificBookLoan_NoAnyBookLoansException() {
        Long userId = 1L;
        Long bookId = 2L;
        when(bookLoanRepository.findByUserAndBook(any(User.class), any(Book.class))).thenReturn(new ArrayList<>());
        when(userService.getUser(userId)).thenReturn(new User());
        when(bookService.getBook(bookId)).thenReturn(new Book());

        assertThrows(NoAnyBookLoansException.class, () -> bookLoanService.getSpecificBookLoan(userId, bookId));
    }
}