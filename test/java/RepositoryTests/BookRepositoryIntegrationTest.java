package RepositoryTests;

import com.libraryassistant.ApiApplication;
import com.libraryassistant.entity.Book;
import com.libraryassistant.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ApiApplication.class)
@ContextConfiguration(classes = BookRepository.class)
public class BookRepositoryIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setTitle("Old Title");
        book.setAuthor("Old Author");
        book.setCount(10);
        bookRepository.save(book);

        Long bookId = book.getId();
        Book persistedBook = bookRepository.findById(bookId).orElse(null);
        assertNotNull(persistedBook);

        String newTitle = "New Title";
        String newAuthor = "New Author";
        Integer newCount = 20;
        bookRepository.updateBook(bookId, newTitle, newAuthor, newCount);

        Book updatedBook = bookRepository.findById(bookId).orElse(null);
        assertNotNull(updatedBook);

        assertEquals(newTitle, updatedBook.getTitle());
        assertEquals(newAuthor, updatedBook.getAuthor());
        assertEquals(newCount, updatedBook.getCount());
    }
}
