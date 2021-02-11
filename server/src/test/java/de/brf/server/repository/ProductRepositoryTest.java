package de.brf.server.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class ProductRepositoryTest {
    /*@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void findAll() {
        persistProducts();

        List<Product> products = productRepository.findAll();
        products.size();
        Assert.assertTrue(products.size() >= createProducts().size());
    }

    @Test
    public void deleteProduct() {
        persistProducts();

        Product product = productRepository.findProductByName("IntelliJ").get();
        productRepository.delete(product);
        Assert.assertTrue(productRepository.findProductByName("IntelliJ").isEmpty());
    }

    @Test
    public void updateProducts() {
        persistProducts();

        Product productFirst = productRepository.findProductByName("Laptop").get();
        Product productSecond = productRepository.findProductById(2L).get();
        String nameBevorRename = productRepository.findProductById(2L).get().getName();

        productFirst.setPrice(BigDecimal.valueOf(566.00));
        productSecond.setName("Windeln");
        productSecond.setCategory(Category.BEAUTY);

        entityManager.persist(productFirst);
        entityManager.flush();

        entityManager.persist(productSecond);
        entityManager.flush();

        Assert.assertTrue(productRepository.findProductByName("Windeln").isPresent());
        Assert.assertEquals(productRepository.findProductByName("Laptop").get().getPrice(), BigDecimal.valueOf(566.00));
        Assert.assertNotSame(productRepository.findProductById(2L).get().getName(), nameBevorRename);

    }

    public List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Nappy","Windel" ,BigDecimal.valueOf(04.99), Category.BABY));
        products.add(new Product("Beak cup", "Schnabelbecher" ,BigDecimal.valueOf(2.76), Category.BABY));
        products.add(new Product("Apple","Apfel",BigDecimal.valueOf(1.00), Category.FOOD));
        products.add(new Product("Citron", "Zitrone",BigDecimal.valueOf(00.90), Category.FOOD));
        products.add(new Product("CoCa Cola","Getränk", BigDecimal.valueOf(1.10), Category.FOOD));
        products.add(new Product("Shampoo","zum Waschen", BigDecimal.valueOf(1.65), Category.BEAUTY));
        products.add(new Product("Playstation 5", "Zum zocken", BigDecimal.valueOf(599.00), Category.COMPUTER_AND_ACCESSORIES));
        products.add(new Product("Laptop","Zum arbeiten etc.",BigDecimal.valueOf(399.00), Category.COMPUTER_AND_ACCESSORIES));
        products.add(new Product("PC","Zum arbeiten etc.",BigDecimal.valueOf(1399.00), Category.COMPUTER_AND_ACCESSORIES));
        products.add(new Product("IntelliJ","Zum Software schreiben", BigDecimal.valueOf(14.99), Category.COMPUTER_AND_ACCESSORIES));

        return products;
    }

    private void persistProducts() {
        for (Product product : createProducts()) {
            entityManager.persist(product);
            entityManager.flush();
        }
    }*/
}
