package services.impl;

import dao.ProfileDAO;
import entity.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ProfileService;

public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOG = LogManager.getLogger(ProfileServiceImpl.class);
    private final ProfileDAO profileDAO;

    public ProfileServiceImpl(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    public Profile getProfileById(long id) {
        return profileDAO.getProfileById(id);
    }

    @Override
    public boolean updateProfile(Profile updatedProfile) {
        return profileDAO.updateProfile(updatedProfile);
    }

    @Override
    public boolean deleteProfileById(long id) {
        return profileDAO.deleteProfileById(id);
    }
}
