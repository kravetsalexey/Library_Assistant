package RepositoryTests;

import com.libraryassistant.ApiApplication;
import com.libraryassistant.entity.User;
import com.libraryassistant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@Transactional
@ContextConfiguration(classes = UserRepository.class)
public class UserRepositoryIntegrationTest {

    @MockBean
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Алексей");
        user.setEmail("alexey@gmail.com");
        entityManager.persist(user);

        String newName = "Иван";
        String newEmail = "ivanivanov@gmail.com";
        Long userId = user.getId();
        userRepository.updateUser(userId, newName, newEmail);

        User updatedUser = entityManager.find(User.class, userId);

        assertNotNull(updatedUser);
        assertEquals(newName, updatedUser.getName());
        assertEquals(newEmail, updatedUser.getEmail());
    }
}