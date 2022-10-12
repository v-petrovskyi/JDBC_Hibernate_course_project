package services;

import entity.Incident;

public interface IncidentService {
    Incident getIncidentById(int id);
    boolean createIncident(Incident incident);
    boolean updateIncident(Incident incident);
    boolean deleteIncidentById(int id);
    boolean closeIncident(int id);
}
