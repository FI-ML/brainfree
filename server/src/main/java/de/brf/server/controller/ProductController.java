package de.brf.server.controller;

import de.brf.server.dto.ProductDto;
import de.brf.server.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/brainfree/")
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping(path = "product/{id}")
    public ResponseEntity<ProductDto> productById(@PathVariable("id") Long id) {
        if (productService.getProductById(id).isPresent()) {
            return new ResponseEntity(productService.getProductById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        if (productService.findProductByName(productDto.getName()).isPresent()) {
            return new ResponseEntity("product already confirmed", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(productService.saveProduct(productDto).get(), HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "products")
    public ResponseEntity<Object> getAllProducts() {
        if (productService.getAllProducts().isEmpty()) {
            return new ResponseEntity<>("no product could be found"
                    , HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productService.getAllProducts()
                    , HttpStatus.OK);
        }
    }
}
