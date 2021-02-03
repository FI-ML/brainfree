package de.brf.server.repository;

import de.brf.server.entity.ShoppingCart;
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
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByName(String name);

    Optional<ShoppingCart> findShoppingCartById(Long id);

    List<ShoppingCart> findAll();

    List<ShoppingCart> findByUser(User user);

    ShoppingCart save(ShoppingCart product);

    void deleteShoppingCartById(Long id);
}
