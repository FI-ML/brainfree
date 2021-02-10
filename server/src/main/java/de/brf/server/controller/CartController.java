package de.brf.server.controller;

import de.brf.server.dto.CartDto;
import de.brf.server.dto.SaveProductToCartDto;
import de.brf.server.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/brainfree/")
public class CartController {

    private final CartService cartService;

    @GetMapping(path = "cart_id/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        if (cartService.findById(id).isPresent()) {
            return new ResponseEntity<>(cartService.findById(id)
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "cart_name/{name}")
    public ResponseEntity<Object> findByName(@PathVariable("name") String name) {
        if (cartService.findByName(name).isPresent()) {
            return new ResponseEntity<>(cartService.findByName(name)
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "create/cart")
    public ResponseEntity<CartDto> saveProduct(@RequestBody SaveProductToCartDto dto, Authentication authentication) {

        if (cartService.saveCart(dto,authentication).isEmpty()) {
            return new ResponseEntity<>(cartService.saveCart(dto, authentication).get(),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(cartService.findByName(dto.getName()).get()
                    , HttpStatus.OK);
        }
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
