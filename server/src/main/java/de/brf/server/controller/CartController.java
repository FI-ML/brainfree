package de.brf.server.controller;

import de.brf.server.dto.CartDto;
import de.brf.server.dto.SaveProductToCartDto;
import de.brf.server.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/brainfree/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        if (cartService.findById(id).isPresent()) {
            return new ResponseEntity<>(cartService.findById(id)
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<CartDto> saveProduct(@RequestBody SaveProductToCartDto dto, Authentication authentication) {
        return cartService.saveCart(dto, authentication).map(cart -> ResponseEntity.ok(cart)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "cart/{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable("id") Long id, @RequestBody SaveProductToCartDto dto) {
        if (cartService.updateCart(id, dto).isPresent()) {
            return new ResponseEntity(cartService.updateCart(id, dto).get()
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
        if (cartService.findById(id).isPresent()) {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
