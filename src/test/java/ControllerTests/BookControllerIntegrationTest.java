package ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryassistant.controller.BookController;
import com.libraryassistant.entity.Book;
import com.libraryassistant.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(BookController.class)
@ContextConfiguration(classes = BookController.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book("Сказки", "Пушкин");
        Mockito.when(bookService.addBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Сказки"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Пушкин"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book("Сказки", "Пушкин");
        Mockito.when(bookService.updateBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Сказки"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Пушкин"));
    }

    @Test
    public void testGetBook() throws Exception {
        Book book = new Book("Сказки", "Пушкин");
        Mockito.when(bookService.getBook(Mockito.anyLong())).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/get")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Сказки"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Пушкин"));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(
                new Book("Сказки", "Пушкин"),
                new Book("Книга", "Чехов")
        );
        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/getall")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Сказки"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("Пушкин"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Книга"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author").value("Чехов"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetMostPopularBook() throws Exception {
        Book book = new Book("Сказки", "Пушкин");
        Mockito.when(bookService.getMostPopularBook(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/mostpopular")
                        .param("startDate", LocalDate.now().minusDays(30).toString())
                        .param("endDate", LocalDate.now().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Сказки"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Пушкин"));
    }
}