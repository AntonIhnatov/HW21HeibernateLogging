package dao.impl;

import entity.User;
import lombok.extern.log4j.Log4j;
import service.UserDaoImpl;

@Log4j
public class UserService {
    private final UserDaoImpl userDao = new UserDaoImpl();

    public void saveUser(User user) {
        log.info("Started saving user");
        if (user == null) {
            log.error("User can not be null!");
        } else {
            userDao.save(user);
            log.warn("Saved user successful with name " + user.getUsername());
        }
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
