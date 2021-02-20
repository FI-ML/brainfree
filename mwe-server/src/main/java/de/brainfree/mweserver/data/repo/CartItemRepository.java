package de.brainfree.mweserver.data.repo;

import de.brainfree.mweserver.data.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
