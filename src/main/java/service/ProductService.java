package dao.impl;

import entity.Product;
import lombok.extern.log4j.Log4j;
import service.ProductDaoImpl;

import java.util.List;

@Log4j
public class ProductService {
    private final ProductDaoImpl customerDao = new ProductDaoImpl();
//    private final Logger logger = Logger.getLogger(ProductService.class);

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
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}
