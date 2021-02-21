package de.brainfree.mweserver.controller;

import de.brainfree.mweserver.dto.ProductDTO;
import de.brainfree.mweserver.exception.NotYetImplementedException;
import de.brainfree.mweserver.mapping.ProductMapper;
import de.brainfree.mweserver.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @ApiOperation(value = "Get all products sorted by name")
    public List<ProductDTO> getAllSortedByName() {
        return productMapper.toDto(productService.getAllSortedByName());
    }

    @PostMapping
    @ApiOperation(value = "Create new product")
    public ProductDTO create(@RequestBody ProductDTO productDto) {
        return productMapper.toDto(productService.createProduct(productDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product with id")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO productDto) {
        throw new NotYetImplementedException(String.format("delete product with id %s", id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete product with id")
    public ProductDTO delete(@PathVariable Long id) {
        throw new NotYetImplementedException(String.format("update product with id %s", id));
    }

}
