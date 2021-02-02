package de.brf.server.repository;

import com.github.javafaker.Faker;
import de.brf.server.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByName() {

        User randomUser = getRandomUser();

        entityManager.persist(randomUser);
        entityManager.flush();

        User found = userRepository.findByFirstNameAndLastName(randomUser.getFirstName(), randomUser.getLastName());

        Assert.assertEquals(found.getFirstName(), randomUser.getFirstName());
    }

    @Test
    public void findByKeycloakId() {
        User randomUser = getRandomUser();

        entityManager.persist(randomUser);
        entityManager.flush();

        User found = userRepository.findByKeycloakId(randomUser.getKeycloakId());

        Assert.assertTrue(userRepository.findByKeycloakId(randomUser.getKeycloakId()) != null);
    }

    @Test
    public void deleteUser() {

        User randomUser = getRandomUser();
        entityManager.persist(randomUser);
        entityManager.flush();

        userRepository.delete(randomUser);

        Assert.assertFalse(userRepository.findByKeycloakId(randomUser.getKeycloakId()) != null);

    }

    private User getRandomUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@amigoscode.com", firstName, lastName);

        return new User(
                firstName,
                lastName,
                email,
                new StringBuilder(firstName + lastName)
                        .reverse()
                        .toString()
        );
    }
}
