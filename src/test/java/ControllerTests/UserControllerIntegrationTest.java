package ControllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryassistant.controller.UserController;
import com.libraryassistant.entity.User;
import com.libraryassistant.service.UserService;
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
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testAddUser() throws Exception {
        User user = new User(1L,"Иван","ivan@example.com");

        given(userService.addUser(any(User.class))).willReturn(user);

        mockMvc.perform(put("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Иван"))
                .andExpect(jsonPath("$.email").value("ivan@example.com"));
    }

    @Test
    public void testUpdateUser() throws Exception{
        User user = new User(1L,"Алексей","alexeykravets@gmail.com");

        when(userService.updateUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Алексей"))
                .andExpect(jsonPath("$.email").value("alexeykravets@gmail.com"));
    }

    @Test
    public void testGetAllUsers() throws Exception{

        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Иван", "ivanivanov@gmail.com"));
        userList.add(new User(2L, "Алексей", "alexeykravets@gmail.com"));

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/getall"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(userList)));
    }

    @Test
    public void testGetUser() throws Exception{
        User user = new User(1L,"Алексей","alexeykravets@gmail.com");

        when(userService.getUser(user.getId())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/get?id=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Алексей"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("alexeykravets@gmail.com"));


    }
    @Test
    public void testDeleteUser() throws Exception{
        User user = new User();
        user.setId(1L);

        when(userService.deleteUser(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete")
                        .param("id","1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService).deleteUser(1L);

    }

    @Test
    public void testGetUserWithMostBookRead() throws Exception{

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        User user = new User(1L, "John Doe", "john@example.com");

        Mockito.when(userService.getUserWithMostBooksRead(startDate, endDate)).thenReturn(user);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/bestreader")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));

    }
}