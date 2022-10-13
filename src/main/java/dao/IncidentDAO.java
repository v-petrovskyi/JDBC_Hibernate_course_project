package dao;

import entity.Incident;

import java.util.List;


public interface IncidentDAO {
    Incident getIncidentById(int id);
    boolean createIncident(Incident incident);
    boolean updateIncident(Incident incident);
    boolean deleteIncidentById(int id);
    List<Incident> getAllIncidents();
}
