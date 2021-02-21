package de.brainfree.mweserver.controller;

import de.brainfree.mweserver.dto.CartItemRequestDTO;
import de.brainfree.mweserver.dto.CartRequestDTO;
import de.brainfree.mweserver.dto.CartResponseDTO;
import de.brainfree.mweserver.exception.NotYetImplementedException;
import de.brainfree.mweserver.mapping.CartMapper;
import de.brainfree.mweserver.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    @GetMapping("/{cartId}")
    @ApiOperation(value = "Get cart by id", response = CartResponseDTO.class)
    public ResponseEntity<CartResponseDTO> getById(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.getById(cartId)));
    }

    @PostMapping
    @ApiOperation(value = "Create cart", response = CartResponseDTO.class)
    public ResponseEntity<CartResponseDTO> create(@RequestBody CartRequestDTO cartDto) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.create(cartDto)));
    }

    @PutMapping("/{cartId}")
    @ApiOperation(value = "Update cart with id", response = CartResponseDTO.class)
    public ResponseEntity<CartResponseDTO> update(@PathVariable Long cartId, @RequestBody CartRequestDTO cartDto) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.update(cartId, cartDto)));
    }

    @DeleteMapping("/{cartId}")
    @ApiOperation(value = "Delete cart with id")
    public ResponseEntity<Void> delete(@PathVariable Long cartId) {
        cartService.delete(cartId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/items")
    @ApiOperation(value = "Add item to cart with id", response = CartResponseDTO.class)
    public ResponseEntity<CartResponseDTO> addItem(@PathVariable Long cartId, @RequestBody CartItemRequestDTO itemDto) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.addItem(cartId, itemDto)));
    }

    @PutMapping("/{cartId}/items/{itemId}")
    @ApiOperation(value = "Update item in cart with id", response = CartResponseDTO.class)
    public ResponseEntity<CartResponseDTO> updateItem(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody CartItemRequestDTO itemDto) {
        throw new NotYetImplementedException(String.format("update items with id %s on cart with id %s", itemId, cartId));
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    @ApiOperation(value = "Remove item from cart with id", response = CartResponseDTO.class)
    public ResponseEntity<CartResponseDTO> removeItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.removeItem(cartId, itemId)));
    }

}
