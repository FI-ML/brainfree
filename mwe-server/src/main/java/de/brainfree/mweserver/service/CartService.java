package de.brainfree.mweserver.service;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.model.CartItem;
import de.brainfree.mweserver.data.repo.CartItemRepository;
import de.brainfree.mweserver.data.repo.CartRepository;
import de.brainfree.mweserver.dto.CartWriteDTO;
import de.brainfree.mweserver.exception.CartNotFoundException;
import de.brainfree.mweserver.mapping.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public Cart getByUsername(String username) {
        return cartRepository.findByUsername(username).orElseThrow(() -> new CartNotFoundException(username));
    }

    public Cart create(Cart cart, Set<CartItem> items) {
        Cart createdCart = cartRepository.save(cart);
        createdCart.setItems(new HashSet<>(items));
        cartItemRepository.saveAll(items);
        return createdCart;
    }

    public Cart update(String username, CartWriteDTO cartDto) {
        Cart cart = getByUsername(username);

        Set<CartItem> cartItems = cartDto.getItems().stream().map(cartItemMapper::toEntity).collect(Collectors.toSet());

        cart.getItems().clear();

        cart.setItems(cartItems);

        cartItemRepository.saveAll(cartItems);

        return cartRepository.save(cart);
    }

}
