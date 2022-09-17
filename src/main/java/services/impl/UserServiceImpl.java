package services.impl;

import dao.UserDAO;
import entity.User;
import services.UserService;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(int id) {

        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userDAO.getUserByUserName(userName);
        if (user == null) {
            System.out.println("Користувача з ім'ям " + userName + " не знайдено");

        }
        return user;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
