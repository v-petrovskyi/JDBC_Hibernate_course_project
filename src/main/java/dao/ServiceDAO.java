package dao;

import entity.Service;


public interface ServiceDAO {
    Service getServiceById(int id);
    boolean createService(Service service);
    boolean updateService(Service service);
    boolean deleteServiceById(int id);
}
