package impl;

import entity.Product;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.ProductDao;
import util.HibernateUtils;

import java.util.List;

@Log4j2
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public void save(Product product) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        try (Session session = factory.openSession()) {
            log.info("Fetching all products from the database");
            TypedQuery<Product> query = session.createQuery("FROM Product", Product.class);
            List<Product> products = query.getResultList();
            log.info("Fetched {} products from the database", products.size());
            return products;
        }
    }

    @Override
    public void updateProduct(int id) {
    }
}
