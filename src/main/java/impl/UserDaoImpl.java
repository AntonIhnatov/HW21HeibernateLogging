package impl;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.UserDao;
import util.HibernateUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public void save(User user) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }


    @Override
    public User getUserById(int id) {
        final Session session = factory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void updateUser(int id) {

    }

    @Override
    public List<User> findAllNatite() {
        return null;
    }
}
