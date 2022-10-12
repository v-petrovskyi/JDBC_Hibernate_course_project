package dao;

import entity.Incident;


public interface IncidentDAO {
    Incident getIncidentById(int id);
    boolean createIncident(Incident incident);
    boolean updateIncident(Incident incident);
    boolean deleteIncidentById(int id);
}
