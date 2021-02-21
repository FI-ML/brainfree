package de.brainfree.mweserver.service;

import de.brainfree.mweserver.data.model.CartItem;
import de.brainfree.mweserver.data.repo.CartItemRepository;
import de.brainfree.mweserver.dto.CartItemRequestDTO;
import de.brainfree.mweserver.exception.CartItemNotFoundException;
import de.brainfree.mweserver.mapping.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartItem getById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new CartItemNotFoundException(id));
    }

    public CartItem create(CartItemRequestDTO itemDto) {
        CartItem item = cartItemMapper.toEntity(itemDto);
        return cartItemRepository.save(item);
    }

    public void delete(CartItem item) {
        cartItemRepository.delete(item);
    }

}
