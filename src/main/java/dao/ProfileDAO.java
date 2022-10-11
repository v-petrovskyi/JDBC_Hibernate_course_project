package dao;

import entity.Profile;

public interface ProfileDAO {
    Profile getProfileById(long id);

    boolean updateProfile(Profile updatedProfile);

    boolean deleteProfileById(long id);
}
