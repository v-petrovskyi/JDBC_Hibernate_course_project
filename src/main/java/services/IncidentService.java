package services;

import entity.Incident;

import java.util.List;

public interface IncidentService {
    Incident getIncidentById(int id);
    boolean addIncident(Incident incident);
    boolean updateIncident(Incident incident);
    boolean deleteIncidentById(int id);
    boolean closeIncident(int id);

    List<Incident> getAllIncidents();
}
