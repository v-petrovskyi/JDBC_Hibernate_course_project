package services;

import entity.User;

public interface UserService {
    User getUserById(int id);
    User getUserByUserName(String userName);
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);

}
