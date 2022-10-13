package services.impl;

import dao.IncidentDAO;
import dao.UserDAO;
import entity.Incident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.IncidentService;

import java.util.List;

public class IncidentServiceImpl implements IncidentService {
    private static final Logger LOG = LogManager.getLogger(IncidentServiceImpl.class);
    private final IncidentDAO incidentDAO;

    public IncidentServiceImpl(IncidentDAO incidentDAO) {
        this.incidentDAO = incidentDAO;
    }

    @Override
    public Incident getIncidentById(int id) {
        return incidentDAO.getIncidentById(id);
    }

    @Override
    public boolean createIncident(Incident incident) {
        return incidentDAO.createIncident(incident);
    }

    @Override
    public boolean updateIncident(Incident incident) {
        return incidentDAO.updateIncident(incident);
    }

    @Override
    public boolean deleteIncidentById(int id) {
        return incidentDAO.deleteIncidentById(id);
    }

    @Override
    public boolean closeIncident(int id) {
        LOG.info("closeIncident id={}", id);
        Incident incident = incidentDAO.getIncidentById(id);
        if (incident == null) {
            System.out.printf("Incident with id %d not found\n",id);
            return false;
        }
        incident.setActive(false);
        return incidentDAO.updateIncident(incident);
    }

    @Override
    public List<Incident> getAllIncidents() {
        LOG.info("getAllIncidents");
        List<Incident> incidents = incidentDAO.getAllIncidents();
        return incidents;
    }
}
