package service.dao;

import entity.Order;
import entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao {

    void save(Order order);

    User getOrderById(int id);

    int getOrderCountByCustomerId(int customerId);

    List<Order> getAllOrdersWithDetailsSortedByTimePlaced();

    List<Order> findAll();

    void updateOrder(int orderId, String comment, LocalDateTime timeUpdated);

    int getTotalQuantityForOrder(int id);

}
