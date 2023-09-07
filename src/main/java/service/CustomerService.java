package service;

import entity.Customer;
import entity.Order;
import entity.User;
import service.impl.CustomerDaoImpl;

import java.util.List;

public class CustomerService {
    private final CustomerDaoImpl customerDao = new CustomerDaoImpl();

    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

        public static Customer createCustomer(String name, String surname, String phone, Object user) {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setSurname(surname);
            customer.setPhone(phone);
            customer.setUser((User) user);
            return customer;
        }

    public List<Customer> getAllCustomersOrders() {
        return customerDao.getAllCustomersWithOrders();
    }
}
