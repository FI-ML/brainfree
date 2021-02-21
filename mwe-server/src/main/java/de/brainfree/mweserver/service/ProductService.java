package de.brainfree.mweserver.service;

import de.brainfree.mweserver.data.model.Product;
import de.brainfree.mweserver.data.repo.ProductRepository;
import de.brainfree.mweserver.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllSortedByName() {
        return productRepository.findAll().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    public Product createProduct(ProductDTO productDto) {
        return productRepository.save(Product.builder()
                .name(productDto.getName())
                .text(productDto.getText())
                .price(productDto.getPrice())
                .build());
    }

}
