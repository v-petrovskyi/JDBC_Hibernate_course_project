package services.impl;

import dao.UserDAO;
import dao.impl.UserDAO_Impl;
import entity.Service;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(int id) {
        LOG.info("getUserById id={}", id);
        User userById = userDAO.getUserById(id);
        return userById;
    }

    @Override
    public User getUserByUserName(String userName) {
        LOG.info("getUserByUserName name={}", userName);
        User user = userDAO.getUserByUserName(userName);
        if (user == null) {
            System.out.println("Користувача з ім'ям " + userName + " не знайдено");
            LOG.info("user with name {} not fonded", userName);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        LOG.info("createUser");
        LOG.info(user);
        userDAO.createUser(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        LOG.info("updateUser");
        LOG.info(user);
        userDAO.updateUser(user);
        return true;
    }

    @Override
    public boolean deleteUserById(int id) {
        LOG.info("delete user with id={}", id);
        if (userDAO.deleteUserById(id)) {
            System.out.printf("користувача з id %d успішно видалено\n", id);
            LOG.info("user with id={} updated successfully", id);
            return true;
        }
        System.out.printf("користувача з id %d не знайдено\n", id);
        LOG.info("user with id={} not founded", id);
        return false;
    }

    @Override
    public boolean subscribeUserToService(User user, Service service) {
        LOG.info("subscribeUserToService");
        LOG.info(user);
        LOG.info(service);
        if (user.getServices().contains(service)) {
            System.out.println("Юзер вже підписаний на даний сервіс");
            return false;
        } else {
            user.getServices().add(service);
            return userDAO.updateUser(user);
        }
    }

    @Override
    public boolean unsubscribeUserFromService(User user, Service service) {
        LOG.info("unsubscribeUserFromService");
        LOG.info(user);
        LOG.info(service);
        for (Service userService : user.getServices()) {
            if (userService.getId() == service.getId()) {
                user.getServices().remove(userService);
                return userDAO.updateUser(user);
            }
        }
        System.out.println("Юзер не підписаний на даний сервіс");
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        LOG.info("getAllUsers");
        List<User> userList = userDAO.getAllUsers();
        if (userList.isEmpty()){
            LOG.info("userDAO.getAllUsers() did not find a user");
            System.out.println("не знайдено жодного юзера");
            return null;
        }
        LOG.info("userDAO.getAllUsers() found users");
        return userList;
    }
}
