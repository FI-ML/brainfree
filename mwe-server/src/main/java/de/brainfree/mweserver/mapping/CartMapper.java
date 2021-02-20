package de.brainfree.mweserver.mapping;

import de.brainfree.mweserver.data.model.Cart;
import de.brainfree.mweserver.data.model.CartItem;
import de.brainfree.mweserver.dto.CartItemReadDTO;
import de.brainfree.mweserver.dto.CartReadDTO;
import de.brainfree.mweserver.dto.CartWriteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
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

    private final CartItemMapper cartItemMapper;

    public CartReadDTO cartToDto(Cart cart) {
        List<CartItemReadDTO> items = cart.getItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList()).stream()
                .sorted(Comparator.comparing(CartItemReadDTO::getProductName))
                .collect(Collectors.toList());

        return CartReadDTO.builder()
                .items(items)
                .priceSum(priceSum(cart.getItems()))
                .build();
    }

    private BigDecimal priceSum(Collection<CartItem> items) {
        //return BigDecimal.TEN;
        return items.stream().map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
