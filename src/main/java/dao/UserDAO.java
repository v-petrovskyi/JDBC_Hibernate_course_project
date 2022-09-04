package dao;

import entity.User;

public interface UserDAO {
    User getUserById(int id);
    User getUserByUserName(String userName);
}
