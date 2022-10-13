package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    User getUserById(long id);
    User getUserByUserName(String userName);
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean deleteUserById(int id);


    List<User> getAllUsers();
}
