package dao.impl;

import dao.ProfileDAO;
import entity.Profile;
import jakarta.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class ProfileDAOImpl implements ProfileDAO {
    private final SessionFactory sessionFactory;
    private static final Logger LOG = LogManager.getLogger(ProfileDAOImpl.class);

    public ProfileDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public Profile getProfileById(long id) {
        LOG.info("get profile by id = {}", id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Profile profile = session.get(Profile.class, id);
            session.getTransaction().commit();
            LOG.info("profile was found");
            LOG.info(profile);
            return profile;
        } catch (NoResultException e) {
            LOG.error(e);
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateProfile(Profile updatedProfile) {
        LOG.info("update profile with id ");
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.merge(updatedProfile);
        session.getTransaction().commit();
        session.close();
        LOG.info("profile updated");
        return false;
    }

    @Override
    public boolean deleteProfileById(long id) {
        LOG.info("delete profile by id = {}", id);
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.remove(getProfileById(id));
        LOG.info("profile removed");
        return false;
    }
}
