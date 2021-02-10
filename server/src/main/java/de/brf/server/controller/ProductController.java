package de.brf.server.controller;

import de.brf.server.dto.ProductDto;
import de.brf.server.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/brainfree/")
public class ProductController {

    private final ProductService productService;


    @GetMapping(path = "product/{id}")
    public ResponseEntity<ProductDto> productById(@PathVariable("id") Long id) {
        if (productService.findById(id).isPresent()) {
            return new ResponseEntity(productService.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "products")
    public ResponseEntity<Object> getAllProducts() {
        if (productService.findAll().isEmpty()) {
            return new ResponseEntity<>("no product could be found"
                    , HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productService.findAll()
                    , HttpStatus.OK);
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


    @PostMapping(path = "products")
    public ResponseEntity<Set<ProductDto>> saveProducts(@RequestBody Set<ProductDto> productsDto) {
        if (productService.saveProducts(productsDto).isEmpty()) {
            return new ResponseEntity(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(productService.saveProducts(productsDto), HttpStatus.CREATED);
        }
    }

}


