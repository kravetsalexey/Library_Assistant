
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryassistant.DTO.BookLoanDto;
import com.libraryassistant.DTO.ReturnBookDto;
import com.libraryassistant.controller.BookLoanController;
import com.libraryassistant.entity.BookLoan;
import com.libraryassistant.service.BookLoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(BookLoanController.class)
@ContextConfiguration(classes = BookLoanController.class)
public class BookLoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookLoanService bookLoanService;

    @Test
    public void testTakeBook() throws Exception {
        BookLoanDto bookLoanDto = new BookLoanDto(1L, 1L);
        Mockito.when(bookLoanService.takeBook(Mockito.any(BookLoanDto.class))).thenReturn(new BookLoan());

        mockMvc.perform(MockMvcRequestBuilders.put("/loan/take")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookLoanDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testReturnBook() throws Exception {
        ReturnBookDto returnBookDto = new ReturnBookDto(1L);
        Mockito.when(bookLoanService.returnBook(Mockito.any(ReturnBookDto.class))).thenReturn(new BookLoan());

        mockMvc.perform(MockMvcRequestBuilders.post("/loan/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(returnBookDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserBookLoans() throws Exception {
        List<BookLoan> bookLoans = Arrays.asList(new BookLoan(), new BookLoan());
        Mockito.when(bookLoanService.getUserBookLoans(Mockito.anyLong())).thenReturn(bookLoans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/getUserLoans")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetBookLoans() throws Exception {
        List<BookLoan> bookLoans = Arrays.asList(new BookLoan(), new BookLoan());
        Mockito.when(bookLoanService.getBookLoans(Mockito.anyLong())).thenReturn(bookLoans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/getBookLoans")
                        .param("bookId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetAllBookLoan() throws Exception {
        List<BookLoan> bookLoans = Arrays.asList(new BookLoan(), new BookLoan());
        Mockito.when(bookLoanService.getAllBookLoans()).thenReturn(bookLoans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/getall")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testDeleteBookLoan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/loan/delete")
                        .param("bookLoanId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetSpecificBookLoan() throws Exception {
        List<BookLoan> bookLoans = Arrays.asList(new BookLoan(), new BookLoan());
        Mockito.when(bookLoanService.getSpecificBookLoan(Mockito.anyLong(), Mockito.anyLong())).thenReturn(bookLoans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/specific")
                        .param("userId", "1")
                        .param("bookId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
}
