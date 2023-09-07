package service.impl;

import entity.Order;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.dao.OrderDao;
import util.HibernateUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public void save(Order order) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        System.out.println("Trying to save order...");
        session.save(order);
        transaction.commit();
        session.close();
        System.out.println("Order saved successfully.");
    }

    @Override
    public int getOrderCountByCustomerId(int customerId) {
        final Session s = factory.openSession();
        final Transaction t = s.beginTransaction();

        int orderCount = ((Number) s.createQuery("SELECT COUNT(o.id) FROM Order o WHERE o.customer.id = :customerId")
                .setParameter("customerId", customerId)
                .getSingleResult()).intValue();
        t.commit();
        s.close();
        return orderCount;

    }


    public List<Long> getProductCountInOrdersByCustomerId(int customerId) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        List<Long> productCounts = session.createQuery(
                        "SELECT COUNT(DISTINCT p.id) " +
                                "FROM Order o " +
                                "LEFT JOIN o.products p " +
                                "WHERE o.customer.id = :customerId " +
                                "GROUP BY o.id", Long.class)
                .setParameter("customerId", customerId)
                .getResultList();

        transaction.commit();
        session.close();

        return productCounts;
    }


    @Override
    public List<Order> getAllOrdersWithDetailsSortedByTimePlaced() {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        List<Order> orders = session.createQuery(
                        "SELECT o " +
                           "FROM Order o " +
                           "LEFT JOIN FETCH o.orderDetails " +
                           "ORDER BY o.orderDetails.timePlaced", Order.class)
                .getResultList();

        transaction.commit();
        session.close();

        return orders;
    }

    @Override
    public void updateOrder(int orderId, String comment, LocalDateTime timeUpdated) {
        final Session s = factory.openSession();
        final Transaction t = s.beginTransaction();
        System.out.println("Trying to set new comment");
        Query query = s.createQuery("UPDATE OrderDetails od SET od.comment = :comment, od.timeUpdated = :timeUpdated WHERE od.order.id = :orderId");
        query.setParameter("comment", comment);
        query.setParameter("orderId", orderId);
        query.setParameter("timeUpdated", timeUpdated);
        query.executeUpdate();
        t.commit();
        s.close();
        System.out.println("Success to set new comment");
    }

    public int getTotalQuantityForOrder(int orderId) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();

        int totalQuantity = ((Number) session.createQuery(
                        "SELECT SUM(op.quantity) " +
                                "FROM Order o " +
                                "JOIN o.products op " +
                                "WHERE o.id = :orderId")
                .setParameter("orderId", orderId)
                .getSingleResult())
                .intValue();

        session.close();

        return totalQuantity;
    }


    @Override
    public User getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

}
