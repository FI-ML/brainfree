package de.brf.server.service;

import de.brf.server.dto.CartDto;
import de.brf.server.dto.SaveProductToCartDto;
import de.brf.server.entity.Cart;
import de.brf.server.entity.Product;
import de.brf.server.repository.CartRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Service
@NoArgsConstructor
@Transactional
public class CartService {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;

    public Optional<CartDto> findById(Long id) {
        return cartRepository.findShoppingCartById(id)
                .map(this::cartToDto);
    }

    public Optional<CartDto> saveCart(SaveProductToCartDto dto,
                                      Authentication authentication) {

        Cart cart = new Cart();

        if (cartRepository.findByName(dto.getName()).isEmpty()) {
            cart.setName(dto.getName());
            cart.setCreateAt(Calendar.getInstance());
            cart.setUser(userService.saveOrGetUser(authentication));
            cart.setProducts(extractProductFrom(dto.getProductIds()));
        }

        return Optional.of(
                cartToDto(cartRepository.save(cart)));
    }

    public Optional<CartDto> findByName(String name) {
        return cartRepository.findByName(name)
                .map(this::cartToDto);
    }


    public Optional<CartDto> updateCart(Long id, SaveProductToCartDto dto) {

        if (cartRepository.findShoppingCartById(id).isEmpty()) {
            return Optional.empty();
        }

        Optional<Cart> cart = cartRepository.findShoppingCartById(id);
        cart.get().setName(dto.getName());
        cart.get().setUpdateAt(Calendar.getInstance());
        cart.get().setProducts(extractProductFrom(dto.getProductIds()));

        return Optional.of(
                cartToDto(cartRepository.save(cart.get())));

    }

    public void deleteCart(Long id) {
        if (cartRepository.findShoppingCartById(id).isPresent()) {
            cartRepository.deleteShoppingCartById(id);
        }
    }

    private Set<Product> extractProductFrom(Set<Long> productIds) {

        return productIds
                .stream()
                .map(id -> productService.findById(id)
                        .map(productService::dtoToProduct)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    private CartDto cartToDto(Cart cart) {
        Set<Long> ids = getProductIdsFromCart(cart);


        return CartDto.builder()
                .name(cart.getName())
                .productIds(ids)
                .createAt(cart.getCreateAt())
                .createAt(cart.getUpdateAt())
                .build();
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
