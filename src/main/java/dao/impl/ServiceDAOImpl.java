package dao.impl;

import dao.ServiceDAO;
import entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class ServiceDAOImpl implements ServiceDAO {
    private static final Logger LOG = LogManager.getLogger(ServiceDAOImpl.class);
    private final SessionFactory sessionFactory;

    public ServiceDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public Service getServiceById(int id) {
        LOG.info("method getServiceById starts looking service with id = {}", id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Service service = session.get(Service.class, id);
        if (service!= null){
            LOG.info("service was found");
            LOG.info(service);
            return service;
        }
        LOG.info("service not found");
        return null;
    }

    @Override
    public boolean createService(Service service) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(service);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateService(Service service) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(service);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteServiceById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Service.class, id));
        return true;
    }
}
