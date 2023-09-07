package service;

import entity.User;
import service.impl.UserDaoImpl;

public class UserService {
    private final UserDaoImpl userDao = new UserDaoImpl();

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User getById(int id) {
        return userDao.getUserById(id);
    }

    public static User createUser(String username, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }
}
