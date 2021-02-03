package de.brf.server.service;

import de.brf.server.dto.ProductDto;
import de.brf.server.dto.ShoppingCartDto;
import de.brf.server.entity.Product;
import de.brf.server.entity.ShoppingCart;
import de.brf.server.repository.ProductRepository;
import de.brf.server.repository.ShoppingCartRepository;
import de.brf.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Service
@AllArgsConstructor
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public List<ShoppingCartDto> getAllShoppingCartsFromUser(String keycloakId) {
        return shoppingCartRepository.findByUser(userRepository.findByKeycloakId(keycloakId)).stream()
                .map(this::shoppingCartToDto)
                .collect(Collectors.toList());
    }

    public Optional<ShoppingCartDto> saveShoppingCart(ShoppingCartDto cartDto) {

        ShoppingCart cart = new ShoppingCart();

        cart.setName(cartDto.getName());
        cart.setCreateAt(new Date());
        cart.setProducts(extractProductsFrom(cartDto));
        cart.setUser(userRepository.findByKeycloakId(cartDto.getKeycloakId()));


        return Optional.of(
                shoppingCartToDto(
                        shoppingCartRepository.save(cart)));
    }

    public Optional<ShoppingCartDto> findByName(String name) {
        return shoppingCartRepository.findByName(name)
                .map(this::shoppingCartToDto);
    }

    public Optional<ShoppingCartDto> updateCart(Long id, ShoppingCartDto cartDto) {

        if (!shoppingCartRepository.findShoppingCartById(id).isPresent()) {
            return Optional.empty();
        }

        Optional<ShoppingCart> card = shoppingCartRepository.findShoppingCartById(id);
        card.get().setName(cartDto.getName());
        card.get().setUpdateAt(new Date());
        card.get().setProducts(extractProductsFrom(cartDto));
        card.get().setUser(userRepository.findByKeycloakId(cartDto.getKeycloakId()));


        return Optional.of(
                shoppingCartToDto(
                        shoppingCartRepository.save(card.get())));

    }

    public Optional<ShoppingCartDto> findById(Long id) {
        return shoppingCartRepository.findShoppingCartById(id)
                .map(this::shoppingCartToDto);
    }

    public void deleteCart(Long id) {

        if (shoppingCartRepository.findShoppingCartById(id).isPresent()) {
            shoppingCartRepository.deleteShoppingCartById(id);
        }

    }


    private Set<Product> extractProductsFrom(ShoppingCartDto cardDto) {
        if (cardDto.getProducts().isEmpty()) {
            return Collections.emptySet();
        }

        Set<Product> products = new HashSet<>();

        for (ProductDto productDto : cardDto.getProducts()) {
            if (productRepository.findProductByName(productDto.getName()).isEmpty()) {
                products.addAll(productService.saveProducts(cardDto.getProducts()));
            } else {
                products.add(productRepository.findProductByName(productDto.getName()).get());
            }
        }
        return products;
    }


    private ShoppingCartDto shoppingCartToDto(ShoppingCart card) {
        return ShoppingCartDto.builder()
                .name(card.getName())
                .products(getProductsFromShoppingCart(card))
                .totalPrice(card.getTotalPrice())
                .keycloakId(card.getUser().getKeycloakId())
                .build();
    }

    private Set<ProductDto> getProductsFromShoppingCart(ShoppingCart card) {
        if (card == null || card.getProducts().isEmpty()) {
            return new HashSet<>();
        }

        return card.getProducts().stream()
                .map(productService::productToDto)
                .collect(Collectors.toSet());
    }

}
