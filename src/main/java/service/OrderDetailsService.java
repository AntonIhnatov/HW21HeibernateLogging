package service;

import entity.Order;
import entity.OrderDetails;
import impl.OrderDetailsDaoImpl;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class OrderDetailsService {
    private final OrderDetailsDaoImpl orderDetailsDao = new OrderDetailsDaoImpl();

    public void saveOrderDetails(OrderDetails orderDetails) {
        log.info("Started saving orderDetails");
        if (orderDetails == null) {
            log.error("orderDetails can not be null!");
        } else {
            orderDetailsDao.save(orderDetails);
            log.warn("Saved orderDetails successful with TimePlaced " + orderDetails.getTimePlaced());
        }
    }


    public static OrderDetails createOrderDetails(String comment, Object order) {
//        log.info("Creating OrderDetails with comment: {}", comment);
        OrderDetails orderDetails = new OrderDetails();
        LocalDateTime now = LocalDateTime.now();
        orderDetails.setTimePlaced(now);
        orderDetails.setTimeUpdated(now);
        orderDetails.setComment(comment);
        orderDetails.setOrder((Order) order);
        return orderDetails;
    }
}
