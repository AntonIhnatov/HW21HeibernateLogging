package service;

import entity.Order;
import entity.OrderDetails;
import service.impl.OrderDetailsDaoImpl;

import java.time.LocalDateTime;

public class OrderDetailsService {
    private final OrderDetailsDaoImpl orderDetailsDao = new OrderDetailsDaoImpl();

    public void saveOrderDetails(OrderDetails orderDetails){
        orderDetailsDao.save(orderDetails);
    }

    public static OrderDetails createOrderDetails(String comment, Object order){
        OrderDetails orderDetails = new OrderDetails();
        LocalDateTime now = LocalDateTime.now();
        orderDetails.setTimePlaced(now);
        orderDetails.setTimeUpdated(now);
        orderDetails.setComment(comment);
        orderDetails.setOrder((Order) order);
        return  orderDetails;
    }
}
