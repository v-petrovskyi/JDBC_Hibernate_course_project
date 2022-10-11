package dao.impl;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionException;
import utils.HibernateUtil;
import dao.UserDAO;
import entity.User;
import org.hibernate.SessionFactory;


public class UserDAO_Impl implements UserDAO {
    private final SessionFactory sessionFactory;
    private static final Logger LOG = LogManager.getLogger(UserDAO_Impl.class);

    public UserDAO_Impl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public User getUserById(int id) {
        LOG.info("method getUserById starts looking user with id = {}", id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        } catch (NoResultException e) {
            LOG.error(e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) throws NoResultException {
        LOG.info("method getUserByUserName starts looking user with name = {}", userName);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userName=:userName", User.class);
        query.setParameter("userName", userName);
        User user = null;
        try {
            user = (User) query.getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (NoResultException e) {
            LOG.error(e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean createUser(User user) {
        LOG.info("add new user to table user");
        LOG.info(user);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        LOG.info("user was successfully added to table user");
        return true;
    }

    @Override
    public boolean updateUser(User updatedUser) {
        LOG.info("update user to table user");
        LOG.info(updatedUser);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User oldUser = session.get(User.class, updatedUser.getId());
        if (updatedUser.getUserName() != null) oldUser.setUserName(updatedUser.getUserName());// todo перенести у сервіс
        if (updatedUser.getPassword() != null) oldUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getUserRole() != null) oldUser.setUserRole(updatedUser.getUserRole());
        if (updatedUser.getProfile() !=null) oldUser.setProfile(updatedUser.getProfile());
        if (updatedUser.getServices()!=null) oldUser.setServices(updatedUser.getServices());
        if(updatedUser.getIncidents()!=null) oldUser.setIncidents(updatedUser.getIncidents());
        LOG.info("user updated successfully");
        LOG.info(oldUser);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteUserById(int id) {
        LOG.info("try to delete user with id {}", id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
            LOG.info("user with id {} deleted successfully", id);
            return true;
        } catch (IllegalArgumentException e) {
            LOG.error(e);
            return false;
        } finally {
            session.close();
        }
    }
}
