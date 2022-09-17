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
        User userById = userDAO.getUserById(id);
        return userById;
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
        userDAO.createUser(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userDAO.updateUser(user);
        return true;
    }

    @Override
    public boolean deleteUserById(int id) {
        if (userDAO.deleteUserById(id)){
            System.out.printf("користувача з id %d успішно видалено\n",id);
            return true;
        }
        System.out.printf("користувача з id %d не знайдено\n",id);
        return false;
    }
}
