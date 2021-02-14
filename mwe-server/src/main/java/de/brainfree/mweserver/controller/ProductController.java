package de.brainfree.mweserver.controller;

import de.brainfree.mweserver.dto.ProductDTO;
import de.brainfree.mweserver.mapping.ProductMapper;
import de.brainfree.mweserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Hier kannst du dann Produkte nach ID updaten, löschen, etc.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> getAllSortedByName() {
        return productMapper.toDto(productService.getAllSortedByName());
    }

}
