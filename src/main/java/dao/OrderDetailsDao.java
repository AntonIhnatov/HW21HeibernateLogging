package service.dao;

import entity.OrderDetails;
import entity.User;

import java.util.List;

public interface OrderDetailsDao {

    void save(OrderDetails orderDetails);

    User getOrderDetailsById(int id);

    List<OrderDetails> findAll();
}
