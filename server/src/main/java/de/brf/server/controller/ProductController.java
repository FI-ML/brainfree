package de.brf.server.controller;

import de.brf.server.dto.ProductDto;
import de.brf.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/brainfree/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .map(productService::productToDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Set<ProductDto>> findAll(){

        Set<ProductDto> products = productService.findAll()
                .stream()
                .map(productService::productToDto)
                .collect(Collectors.toSet());

       if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(products);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto)
                .map(productService::productToDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }
}


