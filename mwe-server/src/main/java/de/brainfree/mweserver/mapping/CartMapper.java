package de.brainfree.mweserver.mapping;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.model.Product;
import de.brainfree.mweserver.dto.CartReadDTO;
import de.brainfree.mweserver.dto.CartWriteDTO;
import de.brainfree.mweserver.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ich hab jetzt hier einiges an Logik in den Mapper gepackt, das ist bissle ausgeartet
 * Man kann auch Mapping Frameworks wie z.b. ModelMapper oder Mapstruct nehmen - das ist dann schlanker
 * Die Logik könnte man dann auch in einen "ProductToCartService" oder so auslagern
 */
@Component
@RequiredArgsConstructor
public class CartMapper {

    private final ProductMapper productMapper;

    public CartReadDTO cartToDto(Cart cart) {
        List<ProductDTO> products = cart.getProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList()).stream()
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
        return CartReadDTO.builder()
                .id(cart.getId())
                .products(products)
                .priceSum(products.stream()
                        .map(ProductDTO::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    public Cart cartToEntity(String username, CartWriteDTO c) {
        Set<Product> products = new HashSet<>();
        for (Long id : c.getProductIds()) {
            productMapper.idToEntity(id).ifPresent(products::add);
        }
        Cart cart = Cart.builder()
                .id(c.getId())
                .username(username)
                .build();
        return cart.updateProducts(products);
    }

}
