package RepositoryTests;

import com.libraryassistant.ApiApplication;
import com.libraryassistant.entity.*;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.BookRepository;
import com.libraryassistant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ApiApplication.class)
@ContextConfiguration(classes = BookLoanRepository.class)
public class BookLoanRepositoryIntegrationTest {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    public void testFindByUserId() {
        User user = new User("Ivan","ivanivanov@gmail.com");
        userRepository.save(user);

        Book book = new Book("Fairy tail","Pushkin");
        bookRepository.save(book);

        BookLoan loan = new BookLoan();
        loan.setUser(user);
        loan.setBorrowedDate(LocalDate.now());

        bookLoanRepository.save(loan);

        List<BookLoan> foundLoans = bookLoanRepository.findByUserId(user.getId());

        assertNotNull(foundLoans);
        assertEquals(1, foundLoans.size());
        assertEquals(user.getId(), foundLoans.get(0).getUser().getId());
    }

    @Test
    @Transactional
    public void testFindMostPopularBookId() {
        User user = new User("Ivan", "ivanivanov@gmail.com");
        userRepository.save(user);

        Book book = new Book("Fairy tail", "Pushkin");
        bookRepository.save(book);

        Book book2 = new Book("Poems", "Pushkin");
        bookRepository.save(book2);

        BookLoan loan = new BookLoan(user, book);
        loan.setBorrowedDate(LocalDate.now());
        bookLoanRepository.save(loan);
        BookLoan loan1 = new BookLoan(user, book);
        loan1.setBorrowedDate(LocalDate.now());
        bookLoanRepository.save(loan1);
        BookLoan loan2 = new BookLoan(user, book2);
        loan2.setBorrowedDate(LocalDate.now());
        bookLoanRepository.save(loan2);

        Long expect = bookLoanRepository.findMostPopularBookId(LocalDate.of(2024,1, 1),LocalDate.of(2024,12,31));

        assertNotNull(expect);
        assertEquals(book.getId(),expect);
    }

    @Test
    @Transactional
    public void testFindUserWithMostBooksRead() {
        User user = new User("Ivan", "ivanivanov@gmail.com");
        userRepository.save(user);

        User user1 = new User("Alexey","alexeykravets@gmail.com");
        userRepository.save(user1);

        Book book = new Book("Fairy tail", "Pushkin");
        bookRepository.save(book);

        Book book2 = new Book("Poems", "Pushkin");
        bookRepository.save(book2);

        BookLoan loan = new BookLoan(user, book);
        loan.setBorrowedDate(LocalDate.now());
        bookLoanRepository.save(loan);
        BookLoan loan1 = new BookLoan(user, book2);
        loan1.setBorrowedDate(LocalDate.now());
        bookLoanRepository.save(loan1);
        BookLoan loan2 = new BookLoan(user1, book2);
        loan2.setBorrowedDate(LocalDate.now());
        bookLoanRepository.save(loan2);

        Long result = bookLoanRepository.findUserWithMostBooksRead(LocalDate.of(2024, 1,1),LocalDate.of(2024,12,31));

        assertNotNull(result);
        assertEquals(result,user.getId());
    }

    @Test
    @Transactional
    public void testFindByBookId() {
        User user = new User("Ivan","ivanivanov@gmail.com");
        userRepository.save(user);

        Book book = new Book("Fairy tail","Pushkin");
        bookRepository.save(book);

        BookLoan loan = new BookLoan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(LocalDate.now());

        bookLoanRepository.save(loan);

        List<BookLoan> foundLoans = bookLoanRepository.findByBookId(book.getId());

        assertNotNull(foundLoans);
        assertEquals(1, foundLoans.size());
        assertEquals(book.getId(), foundLoans.get(0).getBook().getId());
    }

    @Test
    @Transactional
    public void testFindByUserAndBook() {
        User user = new User("Ivan","ivanivanov@gmail.com");
        userRepository.save(user);

        Book book = new Book("Fairy tail","Pushkin");
        bookRepository.save(book);

        BookLoan loan = new BookLoan();
        loan.setUser(user);
        loan.setBook(book);

        bookLoanRepository.save(loan);

        List<BookLoan> foundLoans = bookLoanRepository.findByUserAndBook(user,book);

        assertNotNull(foundLoans);
        assertEquals(1, foundLoans.size());
        assertEquals(book, foundLoans.get(0).getBook());
        assertEquals(user, foundLoans.get(0).getUser());
    }
}
