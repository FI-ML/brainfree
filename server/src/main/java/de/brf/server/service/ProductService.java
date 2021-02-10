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
        Optional<Product> product = Optional.of(dtoToProduct(dto));

        if (productRepository.findProductByName(dto.getName()).isEmpty()) {
            return Optional.of(productToDto(productRepository.save(product.get())));
        } else {
            return Optional.empty();
        }
    }


    public Set<ProductDto> saveProducts(Set<ProductDto> productsDto) {
        return productsDto
                .stream()
                .map(this::saveProduct)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public Set<ProductDto> updateProducts(Set<ProductDto> productDtoList) {
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

    public List<ProductDto> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(this::productToDto)
                .collect(Collectors.toList());
    }


    public Optional<ProductDto> findById(Long id) {

        if (productRepository.findProductById(id).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(
                productToDto(
                        productRepository.findProductById(id).get()
                ));
    }


    public Optional<ProductDto> findProductByName(String productName) {
        if (productRepository.findProductByName(productName).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(
                productToDto(
                        productRepository.findProductByName(productName).get()
                ));
    }



    protected Product dtoToProduct(ProductDto dto) {
        return new Product(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                checkCategory(dto.getCategory()));
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
