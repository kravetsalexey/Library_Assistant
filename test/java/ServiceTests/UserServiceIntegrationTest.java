package ServiceTests;

import com.libraryassistant.entity.User;
import com.libraryassistant.exceptions.EntityAlreadyExistsException;
import com.libraryassistant.exceptions.UserNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.UserRepository;
import com.libraryassistant.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationContext context;

    @Test
    public void testAddUser_Success() {
        User user = new User(1L,"Алексей","alexey@gmail.com");
        when(userRepository.save(user)).thenReturn(user);

        User addedUser =  userService.addUser(user);

        assertNotNull(addedUser);
        assertEquals(user.getName(), addedUser.getName());
        assertEquals(user.getEmail(), addedUser.getEmail());
    }

    @Test
    public void testAddUser_UserAlreadyExists() {

        User user = new User("Алексей","alexey@gmail.com");
        when(userRepository.findAll()).thenReturn(List.of(user));

        assertThrows(EntityAlreadyExistsException.class, () -> userService.addUser(user));
    }

    @Test
    public void testGetUser_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUser(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }

    @Test
    public void testGetUser_UserNotFound() {

        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(userId));
    }

    @Test
    public void testGetUserWithMostBooksRead_Success() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(bookLoanRepository.findUserWithMostBooksRead(startDate, endDate)).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserWithMostBooksRead(startDate, endDate);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }
    @Test
    public void testUpdateUser_Success() {
        Long userId = 1L;
        User user = new User(1L,"Алексей","alexey@gmail.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        assertEquals(user.getName(), updatedUser.getName());
        assertEquals(user.getEmail(), updatedUser.getEmail());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User deletedUser = userService.deleteUser(userId);

        assertNotNull(deletedUser);
        assertEquals(userId, deletedUser.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    }

    @Test
    public void testGetAllUsers_Success() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        userList.add(user1);
        User user2 = new User();
        user2.setId(2L);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUsers = userService.getAllUsers();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
    }

    @Test
    public void testAddUser_InvalidEmail(){
        User user = new User();
        user.setEmail("alexey.com");

    }

}
