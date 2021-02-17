package de.brf.server.repository;

import de.brf.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUUID(String uuid);
}
