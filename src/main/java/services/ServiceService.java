package services;

import entity.Service;

public interface ServiceService {
    Service getServiceById(int id);
    boolean createService(Service service);
    boolean updateService(Service service);
    boolean deleteServiceById(int id);
}
