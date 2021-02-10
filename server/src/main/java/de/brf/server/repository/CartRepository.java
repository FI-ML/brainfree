package de.brf.server.repository;

import de.brf.server.entity.Cart;
import de.brf.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByName(String name);

    Optional<Cart> findShoppingCartById(Long id);

    List<Cart> findAll();

    List<Cart> findByUser(User user);

    Cart save(Cart product);

    void deleteShoppingCartById(Long id);
}
