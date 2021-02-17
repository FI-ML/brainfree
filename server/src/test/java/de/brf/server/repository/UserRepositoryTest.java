package de.brf.server.repository;

import com.github.javafaker.Faker;
import de.brf.server.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUUID() {

        User randomUser = getRandomUser();

        entityManager.persist(randomUser);
        entityManager.flush();

        User found = userRepository.findByUUID(randomUser.getUUID());

        assertEquals(found.getUUID(), randomUser.getUUID());
    }

    @Test
    public void findByKeycloakId() {
        User randomUser = getRandomUser();

        entityManager.persist(randomUser);
        entityManager.flush();

        User found = userRepository.findByUUID(randomUser.getUUID());

        assertNotNull(userRepository.findByUUID(randomUser.getUUID()));
    }

    @Test
    public void deleteUser() {

        User randomUser = getRandomUser();
        entityManager.persist(randomUser);
        entityManager.flush();

        userRepository.delete(randomUser);

        assertNull(userRepository.findByUUID(randomUser.getUUID()));

    }

    private User getRandomUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@amigoscode.com", firstName, lastName);

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .UUID(new StringBuilder(firstName + lastName)
                        .reverse()
                        .toString())
                .build();
    }
}
