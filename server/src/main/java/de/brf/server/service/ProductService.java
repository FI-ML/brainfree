package de.brf.server.service;

import de.brf.server.dto.ProductDto;
import de.brf.server.entity.Product;
import de.brf.server.enums.Category;
import de.brf.server.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @project brainfree
 *
 * @author maximilian lamm brain.free.kontakt@gmail.com
 */


@Service
@AllArgsConstructor
@Transactional
public class ProductService {


    private final ProductRepository productRepository;


    public Optional<ProductDto> saveProduct(ProductDto productDto) {
        Optional<Product> product = Optional.of(dtoToProduct(productDto));

        if (productRepository.findProductByName(productDto.getName()).isEmpty()) {
            return Optional.of(productToDto(productRepository.save(product.get())));
        } else {
            return Optional.empty();
        }
    }


    public Set<Product> saveProducts(Set<ProductDto> productDtoList) {
        Set<Product> products = new HashSet<>();

        if (productDtoList.isEmpty()) {
            return Collections.emptySet();
        }

        for (ProductDto productDto : productDtoList) {
            products.add(productRepository.save(dtoToProduct(productDto)));
        }

        return products;
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

    public List<ProductDto> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(this::productToDto)
                .collect(Collectors.toList());
    }


    public Optional<ProductDto> getProductById(Long id) {

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


    protected Product dtoToProduct(ProductDto productDto) {
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                checkCategory(productDto.getCategory()));
    }

    protected ProductDto productToDto(Product product) {

        return ProductDto.builder()
                .name(product.getName())
                .category(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }


    private Category checkCategory(String pCategory) {
        return Category.valueOf(pCategory);
    }

}
