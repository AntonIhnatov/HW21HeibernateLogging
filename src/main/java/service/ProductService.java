package service;

import entity.Product;
import lombok.extern.log4j.Log4j;
import impl.ProductDaoImpl;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ProductService {
    private final ProductDaoImpl customerDao = new ProductDaoImpl();

    public void saveProduct(Product product) {
        log.info("Started saving product");
        if (product == null) {
            log.error("Product can not be null!");
        } else {
            customerDao.save(product);
            log.warn("Saved product successful with name " + product.getName());
        }
    }

    public List<Product> getAllProducts() {
        return customerDao.findAll();
    }

    public static Product createProduct(String name, double price, int quantity) {
        log.debug("Creating product with name: {}, price: {}, quantity: {}", name, price, quantity);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}
