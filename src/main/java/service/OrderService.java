package service;

import entity.*;
import impl.OrderDaoImpl;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class OrderService {
    private final OrderDaoImpl orderDao = new OrderDaoImpl();

    public void saveOrder(Order order) {
        log.info("Started saving order");
        if (order == null) {
            log.error("Failed to save order. Order object is null.");
        } else {
            try {
                orderDao.save(order);
                log.info("Order saved successfully with name " + order.getName());
            } catch (Exception e) {
                log.error("Failed to save order. Error: " + e.getMessage(), e);
            }
        }
    }

    public int countOrders(int customerId) {
        int orderCount = orderDao.getOrderCountByCustomerId(customerId);
        log.warn("Количество заказов у кастомера №{}: {}", customerId, orderCount);
        System.out.println("Количество заказов у кастомера №" + customerId + ": ");
        return orderCount;
    }


    public static Order createOrder(String name, double totalSum, Customer customer, List<Product> products) {
        log.debug("Creating order with name: {}, totalSum: {}, customer: {}, products: {}", name, totalSum, customer, products);
        Order order = new Order();
        order.setName(name);
        order.setTotalSum(totalSum);
        order.setCustomer(customer);
        order.setProducts(products);
        return order;
    }

    public List<Order> getAllOrdersWithDetails() {
        return orderDao.getAllOrdersWithDetailsSortedByTimePlaced();
    }

    public void updateCustomerOrder(int orderId, String comment, LocalDateTime timeUpdated){
        orderDao.updateOrder(orderId, comment, timeUpdated);
    }

    public int getTotalQuantityForOrder(int id){
        return orderDao.getTotalQuantityForOrder(id);
    }

}
