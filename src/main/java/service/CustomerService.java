package service;

import entity.Customer;
import entity.User;
import impl.CustomerDaoImpl;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CustomerService {
    private final CustomerDaoImpl customerDao = new CustomerDaoImpl();

    public void saveCustomer(Customer customer) {
        log.info("Started saving customer");
        if (customer == null) {
            log.error("Failed to save customer. Customer object is null.");
        } else {
            try {
                customerDao.save(customer);
                log.info("Customer saved successfully with name " + customer.getName());
            } catch (Exception e) {
                log.error("Failed to save Customer. Error: " + e.getMessage(), e);
            }
        }
    }

    public static Customer createCustomer(String name, String surname, String phone, Object user) {
        log.debug("Creating customer with name: {}, surname: {}, role: {}", name, surname, phone);
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPhone(phone);
        customer.setUser((User) user);
        return customer;
    }

    public List<Customer> getAllCustomersOrders() {
        try {
            log.info("Started retrieving all customers with orders");
            List<Customer> customersWithOrders = customerDao.getAllCustomersWithOrders();
            log.info("Finished retrieving all customers with orders. Total customers: {}", customersWithOrders.size());
            return customersWithOrders;
        } catch (Exception e) {
            log.error("Failed to retrieve customers with orders. Error: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve customers with orders", e);
        }
    }
}
