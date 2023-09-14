package service.dao;

import entity.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User getUserById(int id);

    List<User> findAll();

    void updateUser(int id);

    List<User> findAllNatite();
}
