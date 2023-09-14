package dao;

import entity.Customer;
import entity.User;

import java.util.List;

public interface CustomerDao {
    void save(Customer customer);

    User getUserById(int id);

    List<Customer> getAllCustomersWithOrders();

    void updateCustomer(int id);

    List<Customer> findAllNatite();
}
