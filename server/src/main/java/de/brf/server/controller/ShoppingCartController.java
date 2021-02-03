package de.brf.server.controller;

import de.brf.server.dto.ShoppingCartDto;
import de.brf.server.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/brainfree/")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping(path = "cart/{name}")
    public ResponseEntity<Object> getCartByName(@PathVariable("name") String name) {
        if (shoppingCartService.findByName(name).isPresent()) {
            return new ResponseEntity<>(shoppingCartService.findByName(name)
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "carts")
    public ResponseEntity getCarts(@RequestBody ShoppingCartDto shoppingCartDto) {
        if (shoppingCartService.getAllShoppingCartsFromUser(shoppingCartDto.getKeycloakId()).isEmpty()) {
            return new ResponseEntity("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(shoppingCartService.getAllShoppingCartsFromUser(shoppingCartDto.getKeycloakId())
                    , HttpStatus.OK);
        }
    }

    @PostMapping(path = "create/cart")
    public ResponseEntity<ShoppingCartDto> saveProduct(@RequestBody ShoppingCartDto shoppingCartDto) {
        if (shoppingCartService.findByName(shoppingCartDto.getName()).isPresent()) {
            return new ResponseEntity<>(shoppingCartService.findByName(shoppingCartDto.getName()).get()
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shoppingCartService.saveShoppingCart(shoppingCartDto).get(),
                    HttpStatus.CREATED);

        }
    }

    @PutMapping(path = "cart/{id}")
    public ResponseEntity<ShoppingCartDto> updateCart(@PathVariable("id") Long id, @RequestBody ShoppingCartDto shoppingCartDto) {

        if (shoppingCartService.updateCart(id, shoppingCartDto).isPresent()) {
            return new ResponseEntity(shoppingCartService.updateCart(id, shoppingCartDto).get()
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity("no shopping cart could be found"
                    , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {

        if (shoppingCartService.findById(id).isPresent()) {
            shoppingCartService.deleteCart(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
