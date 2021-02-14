package de.brainfree.mweserver.data;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.model.Product;
import de.brainfree.mweserver.data.repo.ProductRepository;
import de.brainfree.mweserver.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final ProductRepository productRepository;
    private final CartService cartService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("DataInitializer start ...");
        initProducts();
        log.info("Products init done ...");
        createCart();
        log.info("Created first cart ...");
    }

    private void createCart() {
        String username = "thomas.maier";
        Cart cart = new Cart();
        cart.setUsername(username);

        Set<Product> products = new HashSet<>();
        getProductByName("Tastatur").ifPresent(products::add);
        getProductByName("Handy").ifPresent(products::add);
        cart.updateProducts(products);

        cartService.update(cart);
    }

    private void initProducts() {
        productRepository.save(Product.builder()
                .name("Tastatur")
                .text("Text zu Tastatur")
                .price(BigDecimal.valueOf(57.99))
                .build());
        productRepository.save(Product.builder()
                .name("Fernseher")
                .text("Text zu Fernseher")
                .price(BigDecimal.valueOf(488.99))
                .build());
        productRepository.save(Product.builder()
                .name("Handy")
                .text("Text zu Handy")
                .price(BigDecimal.valueOf(999.99))
                .build());
    }

    private Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

}
