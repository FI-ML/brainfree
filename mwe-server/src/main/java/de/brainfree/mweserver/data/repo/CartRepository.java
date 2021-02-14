package de.brainfree.mweserver.data.repo;

import de.brainfree.mweserver.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUsername(String username);
}
