package service;

import entity.OrderDetails;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.OrderDetailsDao;
import util.HibernateUtils;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public void save(OrderDetails orderDetails) {
        System.out.println("Trying to save orderDetails...");
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.save(orderDetails);
        transaction.commit();
        session.close();
        System.out.println("OrderDetails saved successfully.");
    }

    @Override
    public User getOrderDetailsById(int id) {
        return null;
    }

    @Override
    public List<OrderDetails> findAll() {
        return null;
    }
}
