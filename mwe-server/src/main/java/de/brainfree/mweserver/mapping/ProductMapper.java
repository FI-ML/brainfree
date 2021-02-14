package de.brainfree.mweserver.mapping;

import de.brainfree.mweserver.data.model.Product;
import de.brainfree.mweserver.data.repo.ProductRepository;
import de.brainfree.mweserver.dto.ProductDTO;
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
public class ProductMapper {

    private final ProductRepository productRepository;

    public ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .text(product.getText())
                .price(product.getPrice())
                .build();
    }

    public List<ProductDTO> toDto(List<Product> products) {
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<Product> idToEntity(Long id) {
        return productRepository.findById(id);
    }

}
