package services;

import entity.Service;

public interface ServiceService {
    Service getServiceById(int id);
    boolean addService(Service service);
    boolean updateService(Service service);
    boolean deleteServiceById(int id);
}
