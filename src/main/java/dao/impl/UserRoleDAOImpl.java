package dao.impl;

import dao.UserRoleDAO;
import entity.UserRole;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class UserRoleDAOImpl implements UserRoleDAO {
    private final SessionFactory sessionFactory;
    private static final Logger LOG = LogManager.getLogger(UserRoleDAOImpl.class);

    public UserRoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public UserRole getUserRoleById(int id) {
        LOG.info("method getUserRoleById starts looking UserRole with id = {}", id);
        Session session = sessionFactory.openSession();
        try {
            UserRole userRole = session.get(UserRole.class, id);
            session.getTransaction().commit();
            return userRole;
        } catch (NoResultException e) {
            LOG.error(e);
        } finally {
            session.close();
        }
        LOG.warn("user role with id={} not found", id);
        return null;
    }

    @Override
    public UserRole getUserRoleByRole(String role) {
        LOG.info("method getUserRoleByRole starts looking UserRole with role={}", role);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserRole where role=:role", UserRole.class);
        query.setParameter("role", role);
        try {
            UserRole userRole = (UserRole) query.getSingleResult();
            session.getTransaction().commit();
        } catch (NoResultException e) {
            LOG.error(e);
        } finally {
            session.close();
        }
        return null;
    }
}
