package services.impl;

import dao.ServiceDAO;
import entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServiceService;

public class ServiceServiceImpl implements ServiceService {
    private static final Logger LOG = LogManager.getLogger(ServiceServiceImpl.class);
    private final ServiceDAO serviceDAO;

    public ServiceServiceImpl(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    @Override
    public Service getServiceById(int id) {
        return serviceDAO.getServiceById(id);
    }

    @Override
    public boolean addService(Service service) {
        return serviceDAO.addService(service);
    }

    @Override
    public boolean updateService(Service service) {
        return serviceDAO.updateService(service);
    }

    @Override
    public boolean deleteServiceById(int id) {
        return serviceDAO.deleteServiceById(id);
    }
}
