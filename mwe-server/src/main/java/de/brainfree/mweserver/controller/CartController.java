package de.brainfree.mweserver.controller;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.dto.CartReadDTO;
import de.brainfree.mweserver.dto.CartWriteDTO;
import de.brainfree.mweserver.mapping.CartMapper;
import de.brainfree.mweserver.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hier alle Aktionen die mit einem Cart zu tun haben
 * Im Frontend brauchst du dafür den username (halt den eingeloggten User sein username) und alle Produkte, damit man die IDs hier ans Backend schicken kann
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    @GetMapping("/{username}")
    public ResponseEntity<CartReadDTO> getById(@PathVariable String username) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.getByUsername(username)));
    }

    /**
     * Nur eine update Methode hat den Vorteil: man kann einfach immer alle Produkte vom Frontend ans Backend schicken
     * Man muss dann nicht irgendwie klären ob da was hinzugefügt oder gelöscht wurde
     *
     * @param username
     * @param cartDto
     * @return
     */
    @PutMapping("/{username}")
    public ResponseEntity<CartReadDTO> update(@PathVariable String username, @RequestBody CartWriteDTO cartDto) {
        return ResponseEntity.ok(cartMapper.cartToDto(cartService.update(username, cartDto)));
    }

}
