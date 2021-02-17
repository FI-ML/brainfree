package de.brf.server.service;

import de.brf.server.dto.ProductDto;
import de.brf.server.entity.Product;
import de.brf.server.enums.Category;
import de.brf.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
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
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return  productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }


    public Optional<Product> saveProduct(ProductDto dto) {
        return Optional.of(dtoToProduct(dto))
                .map(product -> productRepository.save(dtoToProduct(dto)));
    }

    public Set<Product> saveProducts(Set<ProductDto> productsDto) {
        return productsDto
                .stream()
                .map(this::saveProduct)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public Set<Product> updateProducts(Set<ProductDto> productDtoList) {
        if (!productDtoList.isEmpty()) {
            return Collections.emptySet();
        }

        return productDtoList
                .stream()
                .map(this::saveProduct)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

    }



    public ProductDto productToDto(Product product) {

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory().name())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    protected Product dtoToProduct(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(checkCategory(dto.getCategory()))
                .build();
    }

    private Category checkCategory(String pCategory) {
        return Category.valueOf(pCategory);
    }

}
