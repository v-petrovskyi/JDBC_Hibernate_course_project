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

import java.util.List;

public class UserRoleDAOImpl implements UserRoleDAO {
    private final SessionFactory sessionFactory;
    private static final Logger LOG = LogManager.getLogger(UserRoleDAOImpl.class);

    public UserRoleDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public UserRole getUserRoleById(int id) {
        LOG.info("method getUserRoleById starts looking UserRole with id = {}", id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
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
    public UserRole getUserRoleByRole(UserRole.Role role) {
        LOG.info("method getUserRoleByRole starts looking UserRole with role={}", role.toString());
        Session session = sessionFactory.openSession();
        String roleStr = String.valueOf(role);
        Query query = session.createQuery("from UserRole where role=:roleStr", UserRole.class);
        query.setParameter("roleStr", roleStr);
        UserRole userRole = null;
        try {
            userRole = (UserRole) query.getSingleResult();
            session.getTransaction().commit();
        } catch (NoResultException e) {
            LOG.error(e);
        } finally {
            session.close();
        }
        return userRole;
    }

    @Override
    public List<UserRole> getAllRoles() {
        Session session = sessionFactory.openSession();
        List<UserRole> resultList = session.createQuery("select ur from UserRole ur", UserRole.class).getResultList();
        return resultList;
    }
}
