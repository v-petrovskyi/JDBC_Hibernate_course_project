package dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import utils.HibernateUtil;
import dao.UserDAO;
import entity.User;
import org.hibernate.SessionFactory;


public class UserDAO_Impl implements UserDAO {
    private SessionFactory sessionFactory;

    public UserDAO_Impl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userName=:userName", User.class);
        query.setParameter("userName", userName);
        return (User) query.getSingleResult();
    }
}
