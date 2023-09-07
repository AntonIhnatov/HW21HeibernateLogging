package service.dao;

import entity.Product;

import java.util.List;

public interface ProductDao {
    void save(Product product);

    Product getProductById(int id);

    List<Product> findAll();

    void updateProduct(int id);
}
