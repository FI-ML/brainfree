package de.brainfree.mweserver.mapping;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.model.CartItem;
import de.brainfree.mweserver.dto.CartItemResponseDTO;
import de.brainfree.mweserver.dto.CartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ich hab jetzt hier einiges an Logik in den Mapper gepackt, das ist bissle ausgeartet
 * Man kann auch Mapping Frameworks wie z.b. ModelMapper oder Mapstruct nehmen - das ist dann schlanker
 * Die Logik könnte man dann auch in einen "ProductToCartService" oder so auslagern
 */
@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartResponseDTO cartToDto(Cart cart) {
        List<CartItemResponseDTO> items = cart.getItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList()).stream()
                .sorted(Comparator.comparing(CartItemResponseDTO::getProductName))
                .collect(Collectors.toList());

        return CartResponseDTO.builder()
                .name(cart.getName())
                .username(cart.getUsername())
                .items(items)
                .priceSum(priceSum(cart.getItems()))
                .build();
    }

    private BigDecimal priceSum(Collection<CartItem> items) {
        return items.stream().map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
