package util;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    
    private static SessionFactory sessionFactory;
    private static SessionBuilder<SessionBuilder> factory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Customer.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(OrderDetails.class)
                        .addAnnotatedClass(Product.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }

}
