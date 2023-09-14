package service;

import entity.User;
import impl.UserDaoImpl;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserService {
    private final UserDaoImpl userDao = new UserDaoImpl();

    public void saveUser(User user) {
        log.info("Started saving user");
        if (user == null) {
            log.error("Failed to save user. User object is null.");
        } else {
            try {
                userDao.save(user);
                log.info("User saved successfully with name " + user.getUsername());
            } catch (Exception e) {
                log.error("Failed to save user. Error: " + e.getMessage(), e);
            }
        }
    }

    public static User createUser(String username, String email, String role) {
        log.debug("Creating user with username: {}, email: {}, role: {}", username, email, role);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }

    public User getById(int id) {
        return userDao.getUserById(id);
    }
}
