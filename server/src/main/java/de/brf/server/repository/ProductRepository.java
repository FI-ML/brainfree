package de.brf.server.repository;

import de.brf.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
}
