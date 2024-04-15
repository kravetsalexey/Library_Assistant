package RepositoryTests;

import com.libraryassistant.ApiApplication;
import com.libraryassistant.entity.User;
import com.libraryassistant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ApiApplication.class)
@ContextConfiguration(classes = UserRepository.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Alexey");
        user.setEmail("alexey@gmail.com");
        userRepository.save(user);

        Long userId = user.getId();
        User persistedUser = userRepository.findById(userId).orElse(null);
        assertNotNull(persistedUser);

        String newName = "Ivan";
        String newEmail = "ivanivanov@gmail.com";
        userRepository.updateUser(userId, newName, newEmail);

        User updatedUser = userRepository.findById(userId).orElse(null);
        assertNotNull(updatedUser);

        assertEquals(newName, updatedUser.getName());
        assertEquals(newEmail, updatedUser.getEmail());
    }
}
