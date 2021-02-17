package de.brf.server.repository;

import de.brf.server.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByName(String name);
}
