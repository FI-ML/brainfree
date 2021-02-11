package de.brf.server.controller;

import de.brf.server.dto.ProductDto;
import de.brf.server.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/brainfree/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> productById(@PathVariable("id") Long id) {
        return productService.findById(id).map(product -> ResponseEntity.ok(product)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<Object> getAllProducts() {
        if (productService.findAll().isEmpty()) {
            return new ResponseEntity<>("no product could be found"
                    , HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productService.findAll()
                    , HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        if (productService.findProductByName(productDto.getName()).isPresent()) {
            return new ResponseEntity("product already confirmed", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(productService.saveProduct(productDto).get(), HttpStatus.CREATED);
        }
    }

}


