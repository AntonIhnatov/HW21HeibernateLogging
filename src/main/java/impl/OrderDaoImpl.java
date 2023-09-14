package impl;

import entity.Order;
import entity.OrderDetails;
import entity.User;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import dao.OrderDao;
import util.HibernateUtils;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
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

    public List<Order> getAllOrdersWithDetailsSortedByTimePlaced() {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            log.info("Fetching all orders with details and sorting by time placed");
            List<Order> orders = session.createQuery(
                            "SELECT o " +
                                    "FROM Order o " +
                                    "LEFT JOIN FETCH o.orderDetails " +
                                    "ORDER BY o.orderDetails.timePlaced", Order.class)
                    .getResultList();

            transaction.commit();
            log.info("Fetched {} orders with details", orders.size());

            return orders;
        }
    }

    @Override
    public void updateOrder(int orderId, String comment, LocalDateTime timeUpdated) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            log.info("Trying to update OrderDetails for order ID: {}", orderId);

            Query<OrderDetails> query = session.createQuery(
                    "UPDATE OrderDetails od " +
                            "SET od.comment = :comment, od.timeUpdated = :timeUpdated " +
                            "WHERE od.order.id = :orderId");
            query.setParameter("comment", comment);
            query.setParameter("orderId", orderId);
            query.setParameter("timeUpdated", timeUpdated);
            int updatedRowCount = query.executeUpdate();

            transaction.commit();

            if (updatedRowCount > 0) {
                log.info("Successfully updated OrderDetails for order ID: {}", orderId);
            } else {
                log.warn("No records updated for order ID: {}", orderId);
            }
        } catch (Exception e) {
            log.error("Error updating OrderDetails for order ID: {}", orderId, e);
            throw new RuntimeException("Error updating OrderDetails", e);
        }
    }

    public int getTotalQuantityForOrder(int orderId) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = null;
        int totalQuantity = 0;

        try {
            transaction = session.beginTransaction();

            log.info("Calculating total quantity for order ID: {}", orderId);

            totalQuantity = (int) session.createQuery(
                            "SELECT CAST(SUM(op.quantity) AS int) " +
                                    "FROM Order o " +
                                    "JOIN o.products op " +
                                    "WHERE o.id = :orderId")
                    .setParameter("orderId", orderId)
                    .uniqueResult();

            transaction.commit();

            log.info("Total quantity for order ID {} is {}", orderId, totalQuantity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error calculating total quantity for order ID: {}", orderId, e);
            throw new RuntimeException("Error calculating total quantity", e);
        } finally {
            session.close();
        }

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
