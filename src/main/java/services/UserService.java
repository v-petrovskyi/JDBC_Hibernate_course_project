package services;

import entity.Service;
import entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);

    User getUserByUserName(String userName);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(int id);

    boolean subscribeUserToService(User user, Service service);

    boolean unsubscribeUserFromService(User user, Service service);

    List<User> getAllUsers();
}
