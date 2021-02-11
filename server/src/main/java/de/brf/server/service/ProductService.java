package de.brf.server.service;

import de.brf.server.dto.ProductDto;
import de.brf.server.entity.Product;
import de.brf.server.enums.Category;
import de.brf.server.repository.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@NoArgsConstructor
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Optional<ProductDto> saveProduct(ProductDto dto) {
        return productRepository.findByName(dto.getName()).map(product -> productRepository.save(dtoToProduct(dto)));
    }

    public List<ProductDto> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(this::productToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(this::productToDto);
    }


    public Optional<ProductDto> findProductByName(String name) {
        return productRepository.findByName(name).map(this::productToDto);
    }

    protected Product dtoToProduct(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(checkCategory(dto.getCategory()))
                .build();
    }

    protected ProductDto productToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .category(product.getCategory().name())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    private Category checkCategory(String pCategory) {
        return Category.valueOf(pCategory);
    }

}
