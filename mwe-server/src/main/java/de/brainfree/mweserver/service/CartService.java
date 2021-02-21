package de.brainfree.mweserver.service;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.model.CartItem;
import de.brainfree.mweserver.data.repo.CartRepository;
import de.brainfree.mweserver.dto.CartItemRequestDTO;
import de.brainfree.mweserver.dto.CartRequestDTO;
import de.brainfree.mweserver.exception.CartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    public Cart getById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    public Cart create(CartRequestDTO cartDto) {
        Cart cart = new Cart();
        cart.setName(cartDto.getName());
        cart.setUsername(cartDto.getUsername());
        return cartRepository.save(cart);
    }

    public Cart update(Long id, CartRequestDTO cartDto) {
        Cart cart = getById(id);
        cart.setName(cartDto.getName());
        cart.setUsername(cartDto.getUsername());
        return cartRepository.save(cart);
    }

    public void delete(Long id) {
        Cart cart = getById(id);
        cartRepository.delete(cart);
    }

    public Cart addItem(Long id, CartItemRequestDTO itemDto) {
        Cart cart = getById(id);
        CartItem item = cartItemService.create(itemDto);
        return cart.addItem(item);
    }

    public Cart removeItem(Long id, Long itemId) {
        Cart cart = getById(id);
        CartItem item = cartItemService.getById(itemId);
        cartItemService.delete(item);
        return cart.removeItem(item);
    }

}
