package dao.impl;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import utils.HibernateUtil;
import dao.UserDAO;
import entity.User;
import org.hibernate.SessionFactory;


public class UserDAO_Impl implements UserDAO {
    private SessionFactory sessionFactory;
    private static final Logger LOG = LogManager.getLogger(UserDAO_Impl.class);

    public UserDAO_Impl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        LOG.info("method getUserByUserName starts looking user with name = {}", userName);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userName=:userName", User.class);
        query.setParameter("userName", userName);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Користувача з ім'ям " + userName + " не знайдено");
            LOG.error(e);
        }
        return null;
    }
}
