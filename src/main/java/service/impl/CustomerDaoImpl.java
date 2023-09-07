package service.impl;

import entity.Customer;
import entity.Order;
import entity.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.dao.CustomerDao;
import util.HibernateUtils;

import java.math.BigDecimal;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public void save(Customer customer) {
        System.out.println("Trying to save customer...");
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        System.out.println("Customer saved successfully.");
    }

    public List<Customer> getAllCustomersWithOrders() {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        List<Customer> customers = session.createQuery(
                        "SELECT DISTINCT c " +
                                "FROM Customer c " +
                                "LEFT JOIN FETCH c.orders o", Customer.class)
                .getResultList();

        session.close();

        return customers;
    }


    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void updateCustomer(int id) {}

    @Override
    public List<Customer> findAllNatite() {
        return null;
    }
}
