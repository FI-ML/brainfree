package de.brf.server.repository;

import de.brf.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    User findByKeycloakId(String keycloakId);

    User findByFirstNameAndLastName(String firstName, String lastName);
}
