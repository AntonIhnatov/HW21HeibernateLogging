package service;

import entity.Product;
import service.impl.ProductDaoImpl;

import java.util.List;

public class ProductService {
    private final ProductDaoImpl customerDao = new ProductDaoImpl();

    public void saveProduct(Product product) {
        customerDao.save(product);
    }

    public List<Product> getAllProducts(){
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
