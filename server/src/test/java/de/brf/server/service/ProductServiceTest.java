package de.brf.server.service;

import de.brf.server.dto.ProductDto;
import de.brf.server.repository.ProductRepository;
import de.brf.server.repository.ProductRepositoryTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @TestConfiguration
    static class ProductServiceTestConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductService();
        }
    }

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void productToDto() {

        ProductRepositoryTest test = new ProductRepositoryTest();
        ProductDto dto = productService.productToDto(test.createProducts().get(0));

        Assert.assertTrue(dto instanceof ProductDto);
    }
}
