package dao.impl;

import dao.IncidentDAO;
import entity.Incident;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.List;

public class IncidentDAOImpl implements IncidentDAO {
    private final SessionFactory sessionFactory;
    private static final Logger LOG = LogManager.getLogger(IncidentDAOImpl.class);

    public IncidentDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Incident getIncidentById(int id) {
        LOG.info("method getIncidentById starts looking incident with id = {}", id);
        Session session = sessionFactory.openSession();
        try (session) {
            session.beginTransaction();
            Incident incident = session.get(Incident.class, id);
            if (incident != null) {
                LOG.info("incident was found");
                LOG.info(incident);
                return incident;
            }
        } catch (NoResultException e) {
            LOG.error(e);
        }
        LOG.info("incident not found");
        return null;
    }

    @Override
    public boolean createIncident(Incident incident) {
        LOG.info("add new incident to table incident");
        LOG.info(incident);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(incident);
        session.getTransaction().commit();
        session.close();
        LOG.info("incident was successfully added to table incident");
        return true;
    }

    @Override
    public boolean updateIncident(Incident incident) {
        LOG.info("update incident in table incident");
        LOG.info(incident);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(incident);
        LOG.info("incident updated successfully");
        LOG.info(incident);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteIncidentById(int id) {
        LOG.info("try to delete incident with id {}", id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.remove(session.get(Incident.class, id));
            session.getTransaction().commit();
            LOG.info("incident with id {} deleted successfully", id);
            return true;
        } catch (IllegalArgumentException e) {
            LOG.error(e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Incident> getAllIncidents() {
        LOG.info("getAllIncidents starts");
        Session session = sessionFactory.openSession();
        List<Incident> resultList = session.createQuery("select i from Incident i", Incident.class).getResultList();
        session.close();
        return resultList;
    }
}
