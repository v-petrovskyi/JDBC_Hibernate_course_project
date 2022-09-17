package utils;

import dao.UserDAO;
import dao.impl.UserDAO_Impl;
import entity.User;
import entity.UserRole;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.UserService;
import services.impl.UserServiceImpl;

@Data
public class Authorization {
    private static final Logger LOG = LogManager.getLogger(Authorization.class);

    public static UserRole.Role role;
    public static User currentUser;
    private String currentUserName;
    private String currentPassword;


    public Authorization(String currentUserName, String currentPassword) {
        this.currentUserName = currentUserName;
        this.currentPassword = currentPassword;
    }

    public boolean authorizationMethod() {
        LOG.info("start authorization");
        UserService userService = new UserServiceImpl(new UserDAO_Impl());
        User user = userService.getUserByUserName(currentUserName);
        try {
            if (user.getUserName().equals(currentUserName) && user.getPassword().equals(currentPassword)) {
                System.out.printf("Вітаю %s! вхід успішно виконано\n", currentUserName);
                LOG.info("user entered to app as = {}", user.getUserRole().getRole());
                currentUser = user;
                role = user.getUserRole().getRole();
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        System.out.println("логін або пароль введено не вірно");
        return false;
    }

}
