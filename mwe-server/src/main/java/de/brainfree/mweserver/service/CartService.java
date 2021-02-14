package de.brainfree.mweserver.service;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.repo.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Optional<Cart> getByUsername(String name) {
        return cartRepository.findByUsername(name);
    }

    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

}
