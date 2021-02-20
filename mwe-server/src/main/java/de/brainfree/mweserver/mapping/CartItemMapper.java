package de.brainfree.mweserver.mapping;

import de.brainfree.mweserver.data.model.CartItem;
import de.brainfree.mweserver.data.model.Product;
import de.brainfree.mweserver.data.repo.ProductRepository;
import de.brainfree.mweserver.dto.CartItemReadDTO;
import de.brainfree.mweserver.dto.CartItemWriteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * das ist eigentlich ein einfacher Mappper, mit einem Mapping Framework wie Modelmapper oder Mapstruct, könnte man das noch schöner machen
 */
@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductMapper productMapper;

    public CartItemReadDTO toDto(CartItem cartItem) {
        return CartItemReadDTO.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .productText(cartItem.getProduct().getText())
                .productPrice(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }

    public List<CartItemReadDTO> toDto(List<CartItem> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CartItem toEntity(CartItemWriteDTO dto) {
        return CartItem.builder()
                .product(productMapper.idToEntity(dto.getProductId()).get())
                .quantity(dto.getQuantity())
                .build();
    }

}
