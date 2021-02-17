package de.brf.server.service;

import de.brf.server.dto.CartDto;
import de.brf.server.dto.SaveProductToCartDto;
import de.brf.server.entity.Cart;
import de.brf.server.entity.Product;
import de.brf.server.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ProductService productService;

    private final UserService userService;

    private final CartRepository cartRepository;

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> saveCart(SaveProductToCartDto dto,
                                      Authentication authentication) {
        Cart cart = new Cart();

        if (cartRepository.findByName(dto.getName()).isEmpty()) {
            cart.setName(dto.getName());
            cart.setCreatedDate(LocalDateTime.now());
            cart.setUser(userService.saveOrGetUser(authentication));
            cart.setProducts(extractProductFrom(dto.getProductIds()));
        }

        return Optional.of(
               cartRepository.save(cart));
    }

    public Optional<Cart> updateCart(Long id, SaveProductToCartDto dto) {

        if (cartRepository.findById(id).isEmpty()) {
            return Optional.empty();
        }

        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            cart.get().setName(dto.getName());
            cart.get().setCreatedDate(LocalDateTime.now());
            cart.get().setProducts(extractProductFrom(dto.getProductIds()));
            return Optional.of(cartRepository.save(cart.get()));
        }else{
            return Optional.empty();
        }
    }

    public void deleteCart(Long id) {
        cartRepository.findById(id).ifPresent(cartRepository::delete);
    }

    public CartDto cartToDto(Cart cart) {
        Set<Long> ids = getProductIdsFromCart(cart);


        return CartDto.builder()
                .name(cart.getName())
                .productIds(ids)
                .createdDate(cart.getCreatedDate())
                .lastModifiedDate(cart.getLastModifiedDate())
                .build();
    }

    private Set<Product> extractProductFrom(Set<Long> productIds) {

        return productIds
                .stream()
                .map(productService::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }


    private Set<Long> getProductIdsFromCart(Cart cart) {
        return cart
                .getProducts()
                .stream()
                .filter(Objects::nonNull)
                .map(Product::getId)
                .collect(Collectors.toSet());
    }
}
