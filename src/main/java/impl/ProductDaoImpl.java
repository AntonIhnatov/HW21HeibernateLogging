package service;

import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.ProductDao;
import util.HibernateUtils;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public void save(Product product) {
//        System.out.println("Trying to save product...");
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
//        System.out.println("Product saved successfully.");
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        final Session session = factory.openSession();
        List products = session.createQuery("FROM Product").getResultList();
        session.close();
        return products;
    }

    @Override
    public void updateProduct(int id) {

    }
}
