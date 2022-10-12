package services;

import entity.Service;
import entity.User;

public interface UserService {
    User getUserById(int id);

    User getUserByUserName(String userName);

    boolean createUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(int id);

    boolean subscribeUserToService(User user, Service service);

    boolean unsubscribeUserFromService(User user, Service service);
}
