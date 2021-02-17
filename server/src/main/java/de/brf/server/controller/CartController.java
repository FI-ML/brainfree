package de.brf.server.controller;

import de.brf.server.dto.CartDto;
import de.brf.server.dto.SaveProductToCartDto;
import de.brf.server.entity.Cart;
import de.brf.server.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/brainfree/carts/")
public class CartController {

    private final CartService cartService;

    @GetMapping(path = "{id}")
    public ResponseEntity<CartDto> findById(@PathVariable("id") Long id) {
        return cartService.findById(id)
                .map(cartService::cartToDto)
                .map(cart -> ResponseEntity.ok().body(cart))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CartDto> saveProduct(@RequestBody SaveProductToCartDto dto, Authentication authentication) {
        return cartService.saveCart(dto, authentication)
                .map(cartService::cartToDto)
                .map(cart -> ResponseEntity.ok().body(cart))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable("id") Long id, @RequestBody SaveProductToCartDto dto) {
        return cartService.updateCart(id,dto)
                .map(cartService::cartToDto)
                .map(cart -> ResponseEntity.ok().body(cart))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCart(@PathVariable("id") Long id) {

        Optional<Cart> cart = cartService.findById(id);
        if(cart.isPresent()){
            cartService.deleteCart(cart.get().getId());
            return ResponseEntity.noContent().build();
        }else{
           return ResponseEntity.notFound().build();
        }
    }

}
