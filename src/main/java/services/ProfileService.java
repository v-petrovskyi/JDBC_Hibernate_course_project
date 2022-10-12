package services;

import entity.Profile;

public interface ProfileService {
    Profile getProfileById(long id);

    boolean updateProfile(Profile updatedProfile);

    boolean deleteProfileById(long id);
}
